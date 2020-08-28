/**
 * 生物特征平台工具类
 */
package com.tor.utils;

import cn.com.itsea.face.FaceAndFeatureStringExtracted;
import cn.com.itsea.face.ImageVerifyParam;
import cn.com.itsea.face.SDKAPIType;
import cn.com.itsea.faceservice.api.HldfsFaceApiService;
import cn.com.itsea.faceservice.api.helper.HelperOfImageService;
import cn.com.itsea.faceservice.hldfs.impl.HldfsFaceApiServiceImpl;
import cn.com.itsea.hldfs.api.CallResultOfHlDfs;
import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.api.client.ProxyOfHldfsAppServerAccess;
import cn.com.itsea.hldfs.api.client.request.app.ClientRequestOfAppServer;
import cn.com.itsea.hldfs.define.TypeOfServer;
import cn.com.itsea.socket.client.HelperOfProcResultDocument;
import cn.com.itsea.util.FormatedLogAppender;
import cn.com.itsea.util.SafeStringFormater;
import cn.com.itsea.util.XmlHelper;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceUtils {

    private static Integer TIME_OUT = 5;
    private static ServiceUtils SERVICE_UTILS;
    // 省卡管获取照片
    private final static String ACTION_CODE_1 = "supervision.v2.photosofpeoplebyidandnameproc";
    // 医保视图获取参保人员视图
    private final static String ACTION_CODE_2 = "supervision.v2.synchronizeviewdataproc";
    private static String FACE_API_TZZBBH = "2.1.0";
    private static Lock initLock = new ReentrantLock();

    private ServiceUtils() {
    }

    public CompareFeatureDTO compareFeature(byte[] img1Bytes, byte[] img2Bytes) {
        return this.compareFeatureCommon(img1Bytes, img2Bytes, TIME_OUT);
    }

    public CompareFeatureDTO compareFeature(byte[] img1Bytes, String featureImage2) {
        return this.compareFeatureCommon(img1Bytes, featureImage2, TIME_OUT);
    }

    public CompareFeatureDTO compareFeature(String featureImage1, byte[] img2Bytes) {
        return this.compareFeatureCommon(featureImage1, img2Bytes, TIME_OUT);
    }

    public CompareFeatureDTO compareFeature(String featureImage1, String featureImage2) {
        return this.compareFeatureCommon(featureImage1, featureImage2, TIME_OUT);
    }

    public CompareFeatureDTO compareFeature(byte[] img1Bytes, byte[] img2Bytes, int sectimeout) {
        return this.compareFeatureCommon(img1Bytes, img2Bytes, sectimeout);
    }

    public CompareFeatureDTO compareFeature(String featureImage1, String featureImage2, int sectimeout) {
        return this.compareFeatureCommon(featureImage1, featureImage2, sectimeout);
    }

    public CompareFeatureDTO compareFeature(byte[] img1Bytes, String featureImage2, int sectimeout) {
        return this.compareFeatureCommon(img1Bytes, featureImage2, sectimeout);
    }

    public CompareFeatureDTO compareFeature(String featureImage1, byte[] img2Bytes, int sectimeout) {
        return this.compareFeatureCommon(featureImage1, img2Bytes, sectimeout);
    }

    private CompareFeatureDTO compareFeature(Object obj1, Object obj2) {
        return this.compareFeatureCommon(obj1, obj2, TIME_OUT);
    }

    /**
     * 根据身份证号和姓名去省卡管接口获取照片
     *
     * @param idCard 身份证号
     * @param xm     姓名
     * @return 照片byte数组
     */
    public byte[] getPhotosByteByIdCardAndName(String idCard, String xm) {
        Assert.notNull(xm);
        Assert.notNull(idCard);
        Map<String, String> params = new HashMap<>(2);
        params.put("name", xm);
        params.put("idCard", idCard);
        try {
            JSONObject jsonObject = commonRequest(ACTION_CODE_1, params);
            String photoString = jsonObject.getString("result");
            if (StringUtils.isNotBlank(photoString)) {
                return new BASE64Decoder().decodeBuffer(photoString);
            }
        } catch (Exception e) {
            LoggerFactory.getLogger("error").error(LogUtils.getTrace(e));
            return null;
        }
        return null;
    }

    /**
     * 获取医保视图参保人员信息
     *
     * @param grbh
     * @return
     */
    public JSONObject getJzzpViewByGrbh(String grbh) {
        Assert.notNull(grbh);
        Map<String, String> params = new HashMap<>(1);
        params.put("grbh", grbh);
        JSONObject jsonObject = commonRequest(ACTION_CODE_2, params);
        try {
            String resultString = jsonObject.getString("result");
            if (StringUtils.isNotBlank(resultString)) {
                return JSONObject.parseObject(resultString);
            }
        } catch (Exception e) {
            LoggerFactory.getLogger("error").error(LogUtils.getTrace(e));
            return null;
        }
        return null;
    }

    /**
     * 获取照片特征值
     *
     * @param faceImageBytes 照片
     * @return
     */
    public FeatrueDTO extractImageFeature(byte[] faceImageBytes) {
        if (null == faceImageBytes || faceImageBytes.length < 1) {
            throw new IllegalArgumentException("param's image byte[] is not null or empty");
        }
        HldfsFaceApiService hldfsFaceApiService = new HldfsFaceApiServiceImpl(false,
                FactoryOfTrackerServerClientSide.getInstance().getPublicKeyLocalStore(),
                FactoryOfTrackerServerClientSide.getInstance().getMachineId(),
                FactoryOfTrackerServerClientSide.getInstance().getTrackerListPreConfiged(),
                FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null);
        AtomicReference<FaceAndFeatureStringExtracted[]> listResultFacesExtract = new AtomicReference<>();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        FeatrueDTO featrueDTO = new FeatrueDTO();
        try {
            CallResultOfHlDfs resultOfHlDfs = hldfsFaceApiService.extractImageFeature(faceImageBytes,
                    SDKAPIType.HL, false, listResultFacesExtract, 3, logUtil, null);
            if (resultOfHlDfs.isSucc()) {
                FaceAndFeatureStringExtracted faceAndFeatureStringExtracted = listResultFacesExtract.get()[0];
                String featureStr = faceAndFeatureStringExtracted.getFeatureStr();
                if (StringUtils.isNotBlank(featureStr)) {
                    featrueDTO.setSucc(true);
                    featrueDTO.setBbh(FACE_API_TZZBBH);
                    featrueDTO.setFeatureStr(featureStr);
                }
            } else {
                logUtil.setSucc(false).append("faceEngine.errMsg=" + resultOfHlDfs.getErrStr());
            }
            return featrueDTO;
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
            return featrueDTO;
        } finally {
            // 日志处理
            if (logUtil.isSucc()) {
                LoggerFactory.getLogger("root").info(logUtil.getLogString());
            } else {
                LoggerFactory.getLogger("error").error(logUtil.getLogString());
            }
        }
    }

    /**
     * 调用生物特征平台服务公共类
     *
     * @param actionCode
     * @param params
     * @return
     */
    private JSONObject commonRequest(String actionCode, Map<String, String> params) {
        JSONObject result = null;
        ByteArrayOutputStream outArray = new ByteArrayOutputStream(40960);
        FormatedLogUtil logUtil = new FormatedLogUtil();
        AtomicReference<byte[]> resultAttach = new AtomicReference();
        AtomicReference<Document> resultDocument = new AtomicReference();
        try {
            Document requestDocument = HelperOfProcResultDocument.createRequestDocument(actionCode, TIME_OUT);
            Iterator<Map.Entry<String, String>> iterator = params.entrySet().iterator();
            // 设置参数
            while (iterator.hasNext()) {
                Map.Entry<String, String> entry = iterator.next();
                requestDocument.getRootElement().getChildren().add(XmlHelper.getKeyValueElement(entry.getKey(), entry.getValue()));
            }
            ClientRequestOfAppServer requestOfAppServer = new ClientRequestOfAppServer(logUtil, TIME_OUT,
                    FactoryOfTrackerServerClientSide.getInstance().getMachineId(),
                    requestDocument, (byte[]) new ByteArrayOutputStream(40960).toByteArray(),
                    resultAttach, resultDocument, TypeOfServer.APP_SERVER);
            requestOfAppServer.setNeedLogOnTrans(false);
            ProxyOfHldfsAppServerAccess proxyOfHldfsAppServerAccess = new ProxyOfHldfsAppServerAccess(requestOfAppServer,
                    FactoryOfTrackerServerClientSide.getInstance().getPublicKeyLocalStore(), false,
                    FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess().clone(),
                    null, null);
            CallResultOfHlDfs callResultOfHlDfs = proxyOfHldfsAppServerAccess.doRequest(new AtomicReference());
            if (callResultOfHlDfs.isSucc()) {
                AtomicReference<Float> refScore = new AtomicReference<>();
                AtomicInteger refIndex = new AtomicInteger();
                CallResultOfHlDfs resultOfFaceServiceSDK = null;
                resultOfFaceServiceSDK = HelperOfImageService.parseResultFromCompareFeature((Document) resultDocument.get(), logUtil, (byte[]) resultAttach.get(), refScore, refIndex);
                if (null != resultOfFaceServiceSDK && resultOfFaceServiceSDK.isSucc()) {
                    String outputResult = resultDocument.get().getRootElement().getChildren("outputResult").get(0).getTextTrim();
                    String unparse = new SafeStringFormater().unparse(outputResult);
                    result = JSONObject.parseObject(unparse);
                } else {
                    logUtil.setSucc(false).append(resultOfFaceServiceSDK.getErrCode()).append(resultOfFaceServiceSDK.getErrStr());
                }
            } else {
                logUtil.setSucc(false).append("commonRequest failed: " + callResultOfHlDfs.getErrStr());
            }
            return result;
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
            return result;
        } finally {
            // 日志处理
            if (logUtil.isSucc()) {
                LoggerFactory.getLogger("root").error(logUtil.getLogString());
            } else {
                LoggerFactory.getLogger("error").error(logUtil.getLogString());
            }
        }
    }

    /**
     * 人脸识别
     *
     * @param obj1       byte[] 或 String
     * @param obj2       byte[] 或 String
     * @param sectimeout 超时时间 默认 5 秒
     * @return
     */
    private CompareFeatureDTO compareFeatureCommon(Object obj1, Object obj2, int sectimeout) {
        Assert.notNull(obj1);
        Assert.notNull(obj2);
        HldfsFaceApiService hldfsFaceApiService = new HldfsFaceApiServiceImpl(false, FactoryOfTrackerServerClientSide.getInstance().getPublicKeyLocalStore(),
                FactoryOfTrackerServerClientSide.getInstance().getMachineId(),
                FactoryOfTrackerServerClientSide.getInstance().getTrackerListPreConfiged(),
                FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null);
        ImageVerifyParam image1 = createParamUseImage(obj1);
        ImageVerifyParam image2 = createParamUseImage(obj2);
        AtomicReference<Float> refScore = new AtomicReference<>();
        AtomicInteger refIndex = new AtomicInteger();
        FormatedLogAppender loggerAppender = new FormatedLogAppender();
        CompareFeatureDTO featureDTO = new CompareFeatureDTO();
        try {
            CallResultOfHlDfs resultOfHlDfs = hldfsFaceApiService.compareFeature(image1, image2, true, SDKAPIType.HL, refScore, refIndex, sectimeout >= 0 ? sectimeout : TIME_OUT, loggerAppender, null);
            if (resultOfHlDfs.isSucc()) {
                featureDTO.setSucc(true);
                featureDTO.setScore(refScore.get());
                featureDTO.setFaceIndex(refIndex.get());
            }
            return featureDTO;
        } catch (Exception e) {
            e.printStackTrace();
            return featureDTO;
        }
    }

    /**
     * 创建 ImageVerifyParam
     *
     * @param obj 类型只能为 byte[] 或 String
     * @return
     */
    private ImageVerifyParam createParamUseImage(Object obj) {
        Assert.notNull(obj);
        if (obj instanceof byte[]) {
            return ImageVerifyParam.createParamUseImageArray((byte[]) obj, "jpg");
        }
        if (obj instanceof String) {
            return ImageVerifyParam.createParamUseFeatureString((String) obj);
        }
        throw new IllegalArgumentException("ServiceUtils createParamUseImage params Wrong type.");
    }

    /**
     * 获取ServiceUtils实例
     *
     * @return
     */
    public static ServiceUtils getInstance() {
        if (null == SERVICE_UTILS) {
            throw new IllegalArgumentException(" ServiceUtils uninitialized ");
        }
        return SERVICE_UTILS;
    }

    /**
     * 初始化生物特征平台服务工具类
     */
    public static void InitialContext() {
        initLock.lock();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        try {
            if (null == SERVICE_UTILS) {
                boolean isSucc = false;
                logUtil.append("start waitAppServerAvaible [").append(ACTION_CODE_1).append("]");
                isSucc = FactoryOfTrackerServerClientSide.getInstance().waitAppServerAvaible(ServiceUtils.ACTION_CODE_1, 60, logUtil);
                if (!isSucc) {
                    logUtil.setSucc(false).append("ServiceUtils InitialContext failed");
                } else {
                    SERVICE_UTILS = new ServiceUtils();
                    logUtil.append("ServiceUtils InitialContext Success");
                }
            }
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
        } finally {
            if (logUtil.isSucc()) {
                LoggerFactory.getLogger("monitor").info(logUtil.getLogString());
            } else {
                LoggerFactory.getLogger("monitor").error(logUtil.getLogString());
                throw new RuntimeException("ServiceUtils InitialContext failed");
            }
            initLock.unlock();
        }
    }

}