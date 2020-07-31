/**
 * 生物特征平台人脸别对结果类
 */
package com.tor.utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompareFeatureDTO {

    private boolean succ = false;
    private int faceIndex;
    private float score;

}
