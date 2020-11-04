package pattern.prototype.deep;


import java.io.*;
import java.util.Date;
import java.util.Objects;

/**
 * @author jill
 */
public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {
    private static final long serialVersionUID = 5196147874706471254L;
    public JinGuBang jinGuBang;

    public QiTianDaSheng() {
        //只是初始化
        this.birthday = new Date();
        this.jinGuBang = new JinGuBang(1);
    }

    @Override
    public Object clone() {
        return this.deepClone();
    }


    public Object deepClone() {

        //字节数组输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        //根据字节数组输出流产生对象输出流
        ObjectOutputStream oos = null;
        try {
            oos = new ObjectOutputStream(bos);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //对象输出流写入当前对象
        try {
            Objects.requireNonNull(oos).writeObject(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //根据字节数组输出流转化的字节数组产生字节数组输入流
        ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
        //根据字节数组输入流生成对象输入流
        ObjectInputStream ois = null;
        try {
            ois = new ObjectInputStream(bis);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        //从对象输入流里获得对象
        QiTianDaSheng copy = null;
        try {
            copy = (QiTianDaSheng) Objects.requireNonNull(ois).readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        //记录深拷贝的时间
        Objects.requireNonNull(copy).birthday = new Date();
        return copy;
    }

    /**
     * 浅拷贝
     *
     * @param target 浅拷贝对象
     * @return 备份对象
     */
    public QiTianDaSheng shallowClone(QiTianDaSheng target) {

        //只是属性值的拷贝
        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        qiTianDaSheng.height = target.height;
        qiTianDaSheng.weight = target.height;

        qiTianDaSheng.jinGuBang = target.jinGuBang;
        qiTianDaSheng.birthday = new Date();
        return qiTianDaSheng;
    }


}
