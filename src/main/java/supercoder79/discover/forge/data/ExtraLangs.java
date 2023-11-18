package supercoder79.discover.forge.data;

import com.google.gson.*;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;

public class ExtraLangs implements DataProvider {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final PackOutput gen;

    public ExtraLangs(PackOutput gen) {
        this.gen = gen;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Path path = this.gen.getOutputFolder()
                .resolve("assets/discover/lang/en_us.json");

        JsonElement langJson;
        try {
            langJson = JsonParser.parseString(Files.readString(path));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        addExtraLangs(langJson.getAsJsonObject());

        return DataProvider.saveStable(CachedOutput.NO_CACHE, langJson, path);
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
