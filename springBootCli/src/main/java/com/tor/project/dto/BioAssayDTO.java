/**
 * 活体检测结果类
 */
package com.tor.project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BioAssayDTO {
    private boolean succ = false;
    private boolean live;
    private Float score;
}
