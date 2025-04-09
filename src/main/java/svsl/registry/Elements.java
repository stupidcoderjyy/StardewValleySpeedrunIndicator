package svsl.registry;

import svsl.Element;
import svsl.Id;
import svsl.Tag;

import java.util.HashMap;
import java.util.Map;

public class Elements {
    public static final Element WOOD;
    public static final Element STRAWBERRY;
    public static final Element BLUEBERRY;

    private static final Map<Element, Element> BREW_OUTPUT;
    private static final Map<Element, Element> SEED_2_CROP;

    public static final Element STAMINA;

    static {
        BREW_OUTPUT = new HashMap<>();
        SEED_2_CROP = new HashMap<>();

        WOOD = new Element(Id.ofItem("wood"), "木头", Tag.ITEM);
        STRAWBERRY = createFruit("strawberry", "曹美");
        BLUEBERRY = createFruit("blueberry", "蓝莓");

        STAMINA = new Element(Id.ofPlayer("stamina"), "耐力", -15, 270);
    }

    public static Element brewResult(Element ingredient) {
        return BREW_OUTPUT.get(ingredient);
    }

    public static Element seedToCrop(Element crop) {
        return SEED_2_CROP.get(crop);
    }

    private static Element createFruit(String id, String name) {
        var fruit = new Element(Id.ofItem(id), name, Tag.FRUIT_T);
        createSeed(fruit);
        BREW_OUTPUT.put(fruit, new Element(fruit.id.withSuffix("_wine"), fruit.name + "果酒", Tag.FRUIT_WINE));
        return fruit;
    }

    private static void createSeed(Element plant) {
        Element seed = new Element(plant.id.withSuffix("_seed"), plant.name + "种子" + Tag.SEED);
        SEED_2_CROP.put(seed, plant);
    }
}