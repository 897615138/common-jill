package jill.common.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * @author Jill W
 * @date 2020/12/08
 */
public class PageInfo {
    public static final String LIMIT = "limit";
    public static final String OFFSET = "offset";
    private Integer pageOffset;
    private Integer pageLimit;

    public PageInfo() {
    }

    public static PageInfo of(Integer pageNo, Integer size) {
        return new PageInfo(pageNo, size);
    }

    public static PageInfo fromLastId(Integer lastId, Integer size) {
        PageInfo pageInfo = new PageInfo();
        lastId = MoreObjects.firstNonNull(lastId, 0);
        size = MoreObjects.firstNonNull(size, 20);
        pageInfo.pageOffset = lastId > 0 ? lastId : 0;
        pageInfo.pageLimit = size > 0 ? size : 20;
        return pageInfo;
    }

    public PageInfo(Integer pageNo, Integer size) {
        pageNo = MoreObjects.firstNonNull(pageNo, 1);
        size = MoreObjects.firstNonNull(size, 20);
        this.pageLimit = size > 0 ? size : 20;
        this.pageOffset = (pageNo - 1) * size;
        this.pageOffset = this.pageOffset > 0 ? this.pageOffset : 0;
    }

    public Integer getPageOffset() {
        return this.pageOffset;
    }

    public void setPageOffset(Integer pageOffset) {
        this.pageOffset = pageOffset;
    }

    public Integer getPageLimit() {
        return this.pageLimit;
    }

    public void setPageLimit(Integer pageLimit) {
        this.pageLimit = pageLimit;
    }

    public Map<String, Integer> toMap() {
        return this.toMap(null, null);
    }

    public Map<String, Integer> toMap(String key1, String key2) {
        Map<String, Integer> param = Maps.newHashMapWithExpectedSize(2);
        param.put(Strings.isNullOrEmpty(key1) ? "offset" : key1, this.pageOffset);
        param.put(Strings.isNullOrEmpty(key2) ? "limit" : key2, this.pageLimit);
        return param;
    }
    //    return emBannerConfigDao.paging(emBannerConfigCriteria.getOffset(), emBannerConfigCriteria.getLimit(), emBannerConfigCriteria.toMap());
}
