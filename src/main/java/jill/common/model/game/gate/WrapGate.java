package jill.common.model.game.gate;

/**
 * ������
 *
 * @author JIll Wang
 * @date 2020-07-08 08:23
 **/

public class WrapGate {
    public Square s1;
    public Square s2;

    /**
     * �Ҵ����ŵ���һ��
     *
     * @param s ������
     * @return ��һ��������
     */
    public Square getOtherSide(Square s) {
        if (s.equals(s1)) {
            return s2;
        } else {
            if (s.equals(s2)) {
                return s1;
            } else {
                return null;
            }
        }
    }

    public void buildWrapGate(Square s) {
        if (s1 == null) {
            s1 = s;
        } else {
            if (s2 == null) {
                s2 = s;
            } else {
                throw new RuntimeException("too many gates");
            }
        }
    }

}
