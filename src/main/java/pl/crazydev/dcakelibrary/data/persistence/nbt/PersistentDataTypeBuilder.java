package pl.crazydev.dcakelibrary.data.persistence.nbt;

import lombok.AllArgsConstructor;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;
import pl.crazydev.dcakelibrary.data.ByteTools;

@AllArgsConstructor
public class PersistentDataTypeBuilder<T> implements PersistentDataType<byte[], T> {
    private boolean useBukkitStream;
    private Class<T> complex;

    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<T> getComplexType() {
        return complex;
    }

    @Override
    public byte[] toPrimitive(T complex, PersistentDataAdapterContext context) {
        if(useBukkitStream) {
            return ByteTools.bukkitObjectToByteArray(complex);
        }
        return ByteTools.objectToByteArray(complex);
    }

    @Override
    public T fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        if(useBukkitStream) {
            return (T) ByteTools.toBukkitObject(primitive);
        }
        return (T) ByteTools.toObject(primitive);
    }
}
