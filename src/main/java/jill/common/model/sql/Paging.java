package jill.common.model.sql;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * @author Jill W
 * @date 2020/12/08
 */
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = 755183539178076901L;
    private Long total;
    private List<T> data;

    public Paging() {
    }

    public Paging(Long total, List<T> data) {
        this.data = data;
        this.total = total;
    }

    public static <T> Paging<T> empty() {
        return new Paging<>(0L, Collections.emptyList());
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

//    public static <T> Paging<T> empty(Class<T> clazz) {
//        List<T> emptyList = Collections.emptyList();
//        return new Paging(0L, emptyList);
//    }

    public Boolean isEmpty() {
        return Objects.equals(0L, this.total) || this.data == null || this.data.isEmpty();
    }

    public boolean equals(Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Paging)) {
            return false;
        } else {
            Paging other = (Paging) o;
            if (!Objects.equals(this.total, other.total)) {
                return false;
            } else {
                return Objects.equals(this.data, other.data);
            }
        }
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        result = result * 59 + (this.total == null ? 0 : this.total.hashCode());
        result = result * 59 + (this.data == null ? 0 : this.data.hashCode());
        return result;
    }
}
