package supercoder79.discover.forge.data;

import com.google.gson.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.HashCache;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ExtraLangs implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final DataGenerator gen;

    public ExtraLangs(DataGenerator gen) {
        this.gen = gen;
    }

    @Override
    public void run(HashCache cache) throws IOException {
        Path path = this.gen.getOutputFolder()
                .resolve("assets/discover/lang/en_us.json");

        JsonElement langJson = JsonParser.parseString(Files.readString(path));

        addExtraLangs(langJson.getAsJsonObject());

        DataProvider.save(GSON, cache, langJson, path);
    }

    ////////////////////

    private void addExtraLangs(JsonObject obj) {
        obj.addProperty("itemGroup.discover", "Discover");
    }

    ////////////////////

    @Override
    public String getName() {
        return "Discover Extra Lang";
    }
}
