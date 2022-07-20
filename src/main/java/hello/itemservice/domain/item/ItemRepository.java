package hello.itemservice.domain.item;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ItemRepository {

    private static final Map<Long, Item> store = new HashMap<>(); // 저장소
    private static Long sequence = 0L; // 상품고유번호 생성 시퀀스

    // 저장
    public Item save(Item item) {
        item.setId(++sequence);
        store.put(item.getId(), item);
        return item;
    }

    // id 로 조회
    public Item findById(Long itemId) {return store.get(itemId);}

    // 전체조회
    public List<Item> findAll() {return new ArrayList<>(store.values());}

    // id 로 수정
    public void update(Long itemId, Item updateParam) {
        Item item = store.get(itemId);
        item.setItemName(updateParam.getItemName());
        item.setPrice(updateParam.getPrice());
        item.setQuantity(updateParam.getQuantity());
    }

    // 저장소 초기화
    public void clearStore() {store.clear();}
}
