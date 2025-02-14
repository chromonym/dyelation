package pet.cyan.dyelation.configs;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.annotations.Inline;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.collection.ValidatedSet;
import net.minecraft.util.Identifier;
import pet.cyan.dyelation.Dyelation;

public class FarmersDelightConfig extends Config {

    public FarmersDelightConfig() {
        super(Identifier.of(Dyelation.MOD_ID, "farmersdelight"));
    }

    @Comment("""
        A list of dye colors that, when used as the background of a Canvas Sign, should default to white text when placed.
        Dyes: [\"maroon\", \"rose\", \"coral\", \"indigo\", \"navy\", \"slate\", \"olive\", \"amber\", \"beige\", \"teal\", \"mint\", \"aqua\", \"verdant\", \"forest\", \"ginger\", \"tan\"]
        You can actually use *any* dye color (vanilla OR dye depot) here, as well as in the equivalent field of the actual Farmer's Delight config - this field is only kept separate so Dyelation can add its own defaults.
    """)
    @Inline
    public ValidatedSet<String> canvasSignDarkBackgroundList = ValidatedSet.ofString("maroon", "ginger", "verdant", "teal", "slate", "navy", "indigo");
}

