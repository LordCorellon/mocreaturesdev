/*
 * GNU GENERAL PUBLIC LICENSE Version 3
 */
package drzhark.customspawner.biomes;

import net.minecraft.world.biome.Biome;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;
import net.minecraftforge.fml.common.FMLCommonHandler;
import java.lang.reflect.Field;

import java.util.Set;

public class BiomeData {

    private Class<? extends Biome> clazz;
    private boolean defined;
    private Biome biome;
    private String tag;
    private Set<Type> types;

    public BiomeData(Biome biome) {
        this.clazz = biome.getClass();
        this.biome = biome;
        this.defined = false;
    }

    public Class<? extends Biome> getBiomeClass() {
        return this.clazz;
    }

    public String getBiomeName() {
        String biomeName = "";
            MinecraftServer m = FMLCommonHandler.instance().getMinecraftServerInstance();
            if (m.isDedicatedServer()) {
                Field f = ObfuscationReflectionHelper.findField(Biome.class, "field_76791_y");
                f.setAccessible(true);
                try {
                    biomeName = (String) f.get(biome);
                    } catch (IllegalArgumentException e) {
                    } catch (IllegalAccessException e) {
                    }
                return biomeName;
            } else {
                return this.biome.getBiomeName();
            }
        //return this.biome.getBiomeName();
    }

    public Biome getBiome() {
        return this.biome;
    }

    public int getBiomeID() {
        return Biome.getIdForBiome(this.biome);
    }

    public String getTag() {
        return this.tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void setDefined(boolean flag) {
        this.defined = flag;
    }

    public void setTypes(Set<Type> types) {
        this.types = types;
    }

    public Set<Type> getTypes() {
        return this.types;
    }

    public boolean isDefined() {
        return this.defined;
    }
}
