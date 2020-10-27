package pattern.decorator.battercake.v2;


public class Test {
    public static void main(String[] args) {
        AbstractPancake pancake;
        //一个基础煎饼
        pancake = new BasePancake();
        //加一个鸡蛋
        pancake = new EggDecorator(pancake);
        //再加一个鸡蛋
        //很饿，再加根香肠
        pancake = new SausageDecorator(pancake);
        pancake = new SausageDecorator(pancake);
        pancake = new SausageDecorator(pancake);
        pancake = new SausageDecorator(pancake);
        pancake = new SausageDecorator(pancake);

        //跟静态代理最大区别就是职责不同
        //静态代理不一定要满足is-a的关系
        //静态代理会做功能增强，同一个职责变得不一样

        //装饰器更多考虑是扩展

        System.out.println(pancake.getMsg() + ",总价：" + pancake.getPrice());


    }

}
