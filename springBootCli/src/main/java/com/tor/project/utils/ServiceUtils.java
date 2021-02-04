/**
 * 生物特征平台工具类
 */
package com.tor.project.utils;

import cn.com.itsea.face.FaceAndFeatureStringExtracted;
import cn.com.itsea.face.ImageVerifyParam;
import cn.com.itsea.face.SDKAPIType;
import cn.com.itsea.face.v2.bdb.entity.BiologyFingerNum;
import cn.com.itsea.face.v2.client.entity.FaceApiBusiCommonParam;
import cn.com.itsea.face.v2.client.entity.PersonImageCollected;
import cn.com.itsea.faceservice.api.HldfsFaceApiService;
import cn.com.itsea.faceservice.api.helper.HelperOfImageService;
import cn.com.itsea.faceservice.hldfs.impl.HldfsFaceApiServiceImpl;
import cn.com.itsea.faceservice.api.*;
import cn.com.itsea.hldfs.api.CallResultOfHlDfs;
import cn.com.itsea.hldfs.api.client.FactoryOfTrackerServerClientSide;
import cn.com.itsea.hldfs.api.client.ProxyOfHldfsAppServerAccess;
import cn.com.itsea.hldfs.api.client.request.app.ClientRequestOfAppServer;
import cn.com.itsea.hldfs.define.TypeOfServer;
import cn.com.itsea.socket.client.HelperOfProcResultDocument;
import cn.com.itsea.util.FormatedLogAppender;
import cn.com.itsea.util.SafeStringFormater;
import cn.com.itsea.util.XmlHelper;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.fastjson.JSONObject;
import com.tor.common.utils.SpringContextHolder;
import com.tor.project.config.HlServiceConfig;
import com.tor.project.dto.BioAssayDTO;
import com.tor.project.dto.CompareFeatureDTO;
import com.tor.project.dto.FeatrueDTO;
import com.tor.project.dto.Fn35DTO;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayOutputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServiceUtils {

    private static int TIME_OUT = 60;
    private static int WAIT_APP_TIME_OUT = 60;
    private static ServiceUtils SERVICE_UTILS;
    private static String FACE_API_TZZBBH = "2.1.0";
    private final static Lock initLock = new ReentrantLock();

    private static HlFaceWebserviceApiService apiService = SpringContextHolder.getBean(HlFaceWebserviceApiService.class);
    private static PermissionAccessParamOfFaceService permissionAccessParamOfFaceService = SpringContextHolder.getBean(PermissionAccessParamOfFaceService.class);

    /**
     * 省卡管获取照片
     */
    private final static String ACTION_CODE_1 = "supervision.v2.photosofpeoplebyidandnameproc";
    /**
     * 医保视图获取参保人员视图
     */
    private final static String ACTION_CODE_2 = "supervision.v2.synchronizeviewdataproc";
    /**
     * 获取照片特征值code
     */
    private final static String WAIT_ACTION_CODE = "engine.image.feature.extract";

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
     * 只限杭州版本
     *
     * @param idCard 身份证号
     * @param xm     姓名
     * @return 照片byte数组
     */
    public byte[] getPhotosByteByIdCardAndName(String idCard, String xm) {
        Assert.notNull(xm);
        Assert.notNull(idCard);
        Map<String, String> params = new HashMap<>(4);
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
     * 只限制杭州版本
     *
     * @param grbh
     * @return
     */
    public JSONObject getJzzpViewByGrbh(String grbh) {
        Assert.notNull(grbh);
        Map<String, String> params = new HashMap<>(3);
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
     * 活体检测
     *
     * @return
     */
    public BioAssayDTO biologicalAssay(byte[] faceImageBytes) {
        if (null == faceImageBytes || faceImageBytes.length < 1) {
            throw new IllegalArgumentException("param's image byte[] is not null or empty");
        }
        AtomicBoolean liveRef = new AtomicBoolean(false);
        AtomicReference<Float> scoreRef = new AtomicReference<>();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        BioAssayDTO dto = new BioAssayDTO();
        try {
            /*HldfsLivenessDetectApiService apiService =
                    new HldfsLivenessDetectApiServiceImpl(false, FactoryOfTrackerServerClientSide.getInstance().getPublicKeyLocalStore(),
                            FactoryOfTrackerServerClientSide.getInstance().getMachineId(),
                            FactoryOfTrackerServerClientSide.getInstance().getTrackerListPreConfiged(),
                            FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null);
            CallResultOfHlDfs callResultOfHlDfs = apiService.detectSilent(faceImageBytes,liveRef,scoreRef,logUtil,null,TIME_OUT);*/

            CallResultOfHlDfs callResultOfHlDfs = null;
            // TODO 测试环境 测试
            if (true) {
                dto.setSucc(true);
                dto.setLive(Math.random() > 0.5 ? false : true);
                dto.setScore(Math.random() > 0.5 ? 0.81f : 0.70f);
                return dto;
            }
            // TODO 结束
            if (callResultOfHlDfs.isSucc()) {
                dto.setSucc(true);
                dto.setLive(liveRef.get());
                dto.setScore(scoreRef.get());
            } else {
                logUtil.setSucc(false).append("detectSilent.errMsg=" + callResultOfHlDfs.getErrStr());
            }
            return dto;
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
            return dto;
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
                FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null,
                permissionAccessParamOfFaceService);
        AtomicReference<FaceAndFeatureStringExtracted[]> listResultFacesExtract = new AtomicReference<>();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        FeatrueDTO featrueDTO = new FeatrueDTO();
        try {
            CallResultOfHlDfs callResultOfHlDfs = hldfsFaceApiService.extractImageFeature(faceImageBytes,
                    SDKAPIType.HL, false, listResultFacesExtract, TIME_OUT, logUtil, null);
            if (callResultOfHlDfs.isSucc()) {
                FaceAndFeatureStringExtracted faceAndFeatureStringExtracted = listResultFacesExtract.get()[0];
                String featureStr = faceAndFeatureStringExtracted.getFeatureStr();
                if (StringUtils.isNotBlank(featureStr)) {
                    featrueDTO.setSucc(true);
                    featrueDTO.setBbh(FACE_API_TZZBBH);
                    featrueDTO.setFeatureStr(featureStr);
                }
            } else {
                logUtil.setSucc(false).append("faceEngine.errMsg=" + callResultOfHlDfs.getErrStr());
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
     * 根据身份证号和姓名获取照片路径和人员信息mykey
     *
     * @param sfzh
     * @param xm
     * @return
     */
    public Fn35DTO getFn35(String sfzh, String xm) {
        Fn35DTO fn35DTO = new Fn35DTO();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        FaceApiBusiCommonParam busiCommonParam = new FaceApiBusiCommonParam();
        List<PersonImageCollected> listResultImage = new ArrayList<>(16);
        try {
            CallResultOfHlDfs callResultOfHlDfs = apiService.fn35QueryPersonImagesAllCollected(sfzh, xm, busiCommonParam, logUtil, listResultImage);
            if (callResultOfHlDfs.isSucc()) {
                if (CollectionUtil.isNotEmpty(listResultImage)) {
                    fn35DTO.setSucc(true);
                    fn35DTO.setImgs(listResultImage);
                }
            } else {
                logUtil.setSucc(false).append("faceEngine.errMsg=" + callResultOfHlDfs.getErrStr());
            }
            return fn35DTO;
        } catch (Exception e) {
            logUtil.setSucc(false).append(LogUtils.getTrace(e));
            return fn35DTO;
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
     * 根据身份证号和姓名获取照片路径和人员信息mykey
     *
     * @param sfzh
     * @param xm
     * @return
     */
    public FeatrueDTO getFn36(String sfzh, String xm, int mykey) {
        FeatrueDTO featrueDTO = new FeatrueDTO();
        FormatedLogUtil logUtil = new FormatedLogUtil();
        FaceApiBusiCommonParam busiCommonParam = new FaceApiBusiCommonParam();
        AtomicReference<FaceAndFeatureStringExtracted> feature = new AtomicReference<>();
        try {
            CallResultOfHlDfs callResultOfHlDfs = apiService.fn36QueryPersonImageFeatureSpecialIdxCollected(
                    sfzh, xm, SDKAPIType.HL, busiCommonParam, logUtil,
                    new BiologyFingerNum(mykey), feature);
            if (callResultOfHlDfs.isSucc()) {
                if (ObjectUtil.isNotNull(feature.get()) && StringUtils.isNotBlank(feature.get().getFeatureStr())) {
                    featrueDTO.setSucc(true);
                    featrueDTO.setBbh(FACE_API_TZZBBH);
                    featrueDTO.setFeatureStr(feature.get().getFeatureStr());
                }
            } else {
                logUtil.setSucc(false).append("faceEngine.errMsg=" + callResultOfHlDfs.getErrStr());
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
        JSONObject result = new JSONObject();
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
                CallResultOfHlDfs resultOfHlDfs = HelperOfImageService.parseResultFromCompareFeature((Document) resultDocument.get(), logUtil, (byte[]) resultAttach.get(), refScore, refIndex);
                if (null != resultOfHlDfs && resultOfHlDfs.isSucc()) {
                    String outputResult = resultDocument.get().getRootElement().getChildren("outputResult").get(0).getTextTrim();
                    String unparse = new SafeStringFormater().unparse(outputResult);
                    result = JSONObject.parseObject(unparse);
                } else {
                    if (null != resultOfHlDfs) {
                        logUtil.setSucc(false).append(resultOfHlDfs.getErrCode()).append(resultOfHlDfs.getErrStr());
                    }
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
                FactoryOfTrackerServerClientSide.getInstance().createConfigOfServerAccess(), null,
                null);
        ImageVerifyParam image1 = createParamUseImage(obj1);
        ImageVerifyParam image2 = createParamUseImage(obj2);
        AtomicReference<Float> refScore = new AtomicReference<>();
        AtomicInteger refIndex = new AtomicInteger();
        FormatedLogAppender loggerAppender = new FormatedLogAppender();
        CompareFeatureDTO featureDTO = new CompareFeatureDTO();
        try {
            CallResultOfHlDfs callResultOfHlDfs = hldfsFaceApiService.compareFeature(image1, image2, true, SDKAPIType.HL, refScore, refIndex, sectimeout >= 0 ? sectimeout : TIME_OUT, loggerAppender, null);
            if (callResultOfHlDfs.isSucc()) {
                featureDTO.setSucc(true);
                featureDTO.setScore(refScore.get());
                featureDTO.setFaceIndex(refIndex.get());
            } else {
                LoggerFactory.getLogger("error").error(callResultOfHlDfs.getErrCnStr());
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
            initLock.lock();
            try {
                if (null == SERVICE_UTILS) {
                    throw new IllegalArgumentException(" ServiceUtils uninitialized ");
                }
            } finally {
                initLock.unlock();
            }
        }
        return SERVICE_UTILS;
    }

    /**
     * 初始化生物特征平台服务工具类
     */
    public static void initialContext() throws Exception {
        FormatedLogUtil logUtil = new FormatedLogUtil();
        initLock.lock();
        try {
            if (null == SERVICE_UTILS) {
                boolean isSucc = false;
                logUtil.append("start waitAppServerAvaible [").append(WAIT_ACTION_CODE).append("]");
                isSucc = FactoryOfTrackerServerClientSide.getInstance()
                        .waitAppServerAvaible(ServiceUtils.WAIT_ACTION_CODE, WAIT_APP_TIME_OUT, logUtil);
                if (!isSucc) {
                    logUtil.setSucc(false).append("ServiceUtils initialContext failed");
                    throw new IllegalArgumentException("ServiceUtils initialContext failed");
                } else {
                    SERVICE_UTILS = new ServiceUtils();
                    logUtil.append("ServiceUtils initialContext Success");
                }
            }
        } catch (Exception e) {

        } finally {
            initLock.unlock();
            if (logUtil.isSucc()) {
                LoggerFactory.getLogger("monitor").info(logUtil.getLogString());
            } else {
                LoggerFactory.getLogger("monitor").error(logUtil.getLogString());
            }
        }
    }

    public static void main(String[] args) {
        /*new HlServiceConfig().init();
        String path = "hldfs.v100.s101.20200828.10000016e072aac2d.dat";
        byte[] bytes = FileStorager.download(path);
        if(null != bytes){
            BioAssayDTO bioAssayDTO = ServiceUtils.getInstance().biologicalAssay(bytes);
            if(bioAssayDTO.isSucc()){
                Console.log(bioAssayDTO.toString());
                System.exit(1);
            }
        }*/
    }
}