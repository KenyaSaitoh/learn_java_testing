package pro.kensait.spring.mvc.calc;

public class CalcForm {
    // パラメータ1
    private Double param1;

    // パラメータ2
    private Double param2;

    public Double getParam1() {
        return param1;
    }

    public void setParam1(Double param1) {
        this.param1 = param1;
    }

    public Double getParam2() {
        return param2;
    }

    public void setParam2(Double param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return "CalcForm [param1=" + param1 + ", param2=" + param2 + "]";
    }
}
