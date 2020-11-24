package initial;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

public class TestMultiMap {
    
    public static void main(String[] args) {
        
        ListMultimap<Ingredient, Recipe> treeListMultiMap = MultimapBuilder.treeKeys().arrayListValues().build();

    }

}
