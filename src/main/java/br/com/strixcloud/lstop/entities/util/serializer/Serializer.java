package br.com.strixcloud.lstop.entities.util.serializer;

public interface Serializer<K, V> {

    V serialize(K value);

    K deserialize(V value);

}
