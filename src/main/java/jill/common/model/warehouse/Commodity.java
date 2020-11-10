package jill.common.model.warehouse;

import java.util.Objects;

/**
 * 商品类
 *
 * @author JIll Wang
 * @date 2020-07-14 17:56
 **/
public class Commodity {
    /**
     * 商品名
     */
    private String name;
    /**
     * 商品存量
     */
    private Integer number;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Commodity)) {
            return false;
        }
        Commodity commodity = (Commodity) o;
        return Objects.equals(getName(), commodity.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Commodity{" +
                "name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}