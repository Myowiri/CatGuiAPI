//package meow.cats;
//
//import meow.cats.items.*;
//
//import java.awt.*;
//
//public class TestGui extends CatGui{
//
//    public static TestGui Instance;
//
//    public TestGui(){
//        setInstance();
//    }
//    static{
//        Instance = new TestGui();
//    }
//
//    private void setInstance() {
//        Instance = this;
//    }
//
//
//    @Override
//    public void addButtons(){
//        buttons.add(new CentedText("Text").setWidth(200).setDx(-20));
//        buttons.add(new BooleanButton(true, "Test", v -> System.out.println(1)));
//        buttons.add(new ColorPicker(new Color(-1), "Test", v -> System.out.println(1)));
//        buttons.add(new Text("Cats are popular domestic pets. They belong to the feline family. They have soft fur, sharp claws, and excellent night vision. Cats are independent, playful, and love to sleep."));
//        buttons.add(new Slider("Test", 1f, 0f, 10f, v -> System.out.println(1)));
//        buttons.add(new ColorPicker(new Color(-1), "Test", v -> System.out.println(1)).doNotSpace().setWidth(78));
//        buttons.add(new ColorPicker(new Color(-1), "Test", v -> System.out.println(1)).setDx(82).setWidth(78));
//    }
//
//
//
//}
