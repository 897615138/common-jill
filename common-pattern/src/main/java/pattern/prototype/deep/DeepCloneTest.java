package pattern.prototype.deep;


/**
 * @author jill
 */
public class DeepCloneTest {

    public static void main(String[] args) {

        QiTianDaSheng qiTianDaSheng = new QiTianDaSheng();
        System.out.println("拷贝前" + qiTianDaSheng.jinGuBang.getIndex());
        try {
            QiTianDaSheng clone = (QiTianDaSheng) qiTianDaSheng.clone();
            System.out.println("深克隆：" + (qiTianDaSheng.jinGuBang == clone.jinGuBang));
            System.out.println("更改源后");
            qiTianDaSheng.jinGuBang.setIndex(2);
            System.out.println("源" + qiTianDaSheng.jinGuBang.getIndex());
            System.out.println("克隆" + clone.jinGuBang.getIndex());
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println("---------------");
        QiTianDaSheng q = new QiTianDaSheng();
        QiTianDaSheng n = q.shallowClone(q);
        System.out.println("浅克隆：" + (q.jinGuBang == n.jinGuBang));
        qiTianDaSheng.jinGuBang.setIndex(2);
        System.out.println("源" + q.jinGuBang.getIndex());
        System.out.println("克隆" + n.jinGuBang.getIndex());

    }
}
