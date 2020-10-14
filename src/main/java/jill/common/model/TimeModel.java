package jill.common.model;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * @author JillW
 * @date 2020/10/10
 */
@Data
@Builder
@ToString
public class TimeModel {
private Date startAt;
private Date endAt;
}
