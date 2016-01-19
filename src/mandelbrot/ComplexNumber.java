package mandelbrot;

public class ComplexNumber {
    double a;
    double b;
    
    public ComplexNumber(double realPart, double imaginaryPart) {
        a = realPart;
        b = imaginaryPart;
    }
    
    public void display() {
        String s = "";
        if (a != 0) {
            s += a;
        }
        if (b != 0) {
            if (b > 0) {
                s += "+" + b + "i";
            } else {
                s += b + "i";
            }
        }
        System.out.println(s);
    }
    
    public double abs() {
        return Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2));
    }
    
    public ComplexNumber conj() {
        return new ComplexNumber(a, -b);
    }
    
    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(c.a + a, c.b + b);
    }
    
    public ComplexNumber multiply(double d) {
        return new ComplexNumber(a * d, b * d);
    }
    
    public ComplexNumber divide(double d) {
        return new ComplexNumber(a / d, b / d);
    }
    
    public ComplexNumber multiply(ComplexNumber c) {
        return new ComplexNumber(a * c.a - b * c.b, c.a * b + a * c.b);
    }
    
    public ComplexNumber divide(ComplexNumber c) {
        return this.multiply(c.conj()).divide(Math.pow(c.abs(), 2));
    }
    
    public ComplexNumber exp(int i) {
        ComplexNumber c = this;
        for (int j = 0; j < i; j++) {
            c = c.multiply(this);
        }
        return c;
    }
}
