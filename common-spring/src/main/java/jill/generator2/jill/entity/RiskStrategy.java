package jill.generator2.jill.entity;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 风险识别策略表
 * </p>
 *
 * @author jill
 * @since 2020-12-14
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="RiskStrategy对象", description="风险识别策略表")
public class RiskStrategy implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "策略名称")
    private String name;

    @ApiModelProperty(value = "触发场景code")
    private String touchScene;

    @ApiModelProperty(value = "风控措施")
    private String controlAction;

    @ApiModelProperty(value = "状态:0草稿 1已启用 2已停用")
    private Integer strategyState;

    @ApiModelProperty(value = "创建者id")
    private String createId;

    @ApiModelProperty(value = "创建者名称")
    private String createName;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "创建者id")
    private String updateId;

    @ApiModelProperty(value = "更新者名称")
    private String updateName;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "是否删除")
    private Integer delFlag;

    @ApiModelProperty(value = "其他信息")
    private String extraJson;


}
