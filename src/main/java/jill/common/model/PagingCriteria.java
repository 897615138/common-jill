package jill.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

/**
 * @author Jill W
 * @date 2020/12/08
 */
public class PagingCriteria extends Criteria implements Serializable {
    private static final long serialVersionUID = 2598875146576926658L;
    public static final int SORT_TYPE_ASC = 1;
    public static final int SORT_TYPE_DESC = 2;
    @JsonIgnore
    protected Integer pageNo = 1;
    @JsonIgnore
    protected Integer pageSize = 20;
    @JsonIgnore
    protected Boolean hasNext = true;
    protected Boolean skipCount;
    protected String sortBy;
    protected Integer sortType;

    public PagingCriteria() {
    }

    @JsonIgnore
    public Boolean hasNext() {
        return this.hasNext;
    }

    public void setHasNext(Boolean hasNext) {
        this.hasNext = hasNext;
    }

    public MoreObjects.ToStringHelper toStringHelper() {
        return MoreObjects.toStringHelper(this).add("pageNo", this.pageNo).add("pageSize", this.pageSize).add("hasNext", this.hasNext).add("skipCount", this.skipCount).add("sortBy", this.sortBy).add("sortType", this.sortType);
    }

    public void nextPage() {
        if (this.pageNo == null) {
            this.pageNo = 1;
        }

        this.pageNo = this.pageNo + 1;
    }

    public Integer getLimit() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getPageLimit();
    }

    public Integer getOffset() {
        PageInfo pageInfo = new PageInfo(this.pageNo, this.pageSize);
        return pageInfo.getPageOffset();
    }

    public Map<String, Object> toMap() {
        return super.toMap();
    }

    public Integer getPageNo() {
        return this.pageNo;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public String getSortBy() {
        return this.sortBy;
    }

    public Integer getSortType() {
        return this.sortType;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public void setSortType(Integer sortType) {
        this.sortType = sortType;
    }

    public Boolean getSkipCount() {
        return this.skipCount;
    }

    public void setSkipCount(Boolean skipCount) {
        this.skipCount = skipCount;
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof PagingCriteria)) {
            return false;
        } else {
            PagingCriteria other = (PagingCriteria)o;
            if (!Objects.equals(this.pageNo, other.pageNo)) {
                return false;
            } else if (!Objects.equals(this.pageSize, other.pageSize)) {
                return false;
            } else if (!Objects.equals(this.hasNext, other.hasNext)) {
                return false;
            } else if (!Objects.equals(this.sortBy, other.sortBy)) {
                return false;
            } else {
                return Objects.equals(this.sortType, other.sortType);
            }
        }
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.pageNo == null ? 0 : this.pageNo);
        result = result * 59 + (this.pageSize == null ? 0 : this.pageSize);
        result = result * 59 + (this.hasNext == null ? 0 : this.hasNext.hashCode());
        result = result * 59 + (this.sortBy == null ? 0 : this.sortBy.hashCode());
        result = result * 59 + (this.sortType == null ? 0 : this.sortType.hashCode());
        return result;
    }
}