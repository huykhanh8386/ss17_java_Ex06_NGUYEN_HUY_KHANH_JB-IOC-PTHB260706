package Ex06;

public class Main {
    public static void main(String[] args) {
        EmployeeBonusService bonusService = new EmployeeBonusService();
        System.out.println("        TÍNH THƯỞNG KPI NHÂN VIÊN VỚI CALLABLE STATEMENT");
        bonusService.calculateAndPrintBonus(1);
        bonusService.calculateAndPrintBonus(9999);
    }
}