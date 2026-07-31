public class TestDriver {
    public static void main(String[] args) {
        MainMenu mainMenu = new MainMenu();
        Maze mazeView = new Maze(1 ,1);
        TestController test = new TestController(mainMenu, mazeView);
    }
}
