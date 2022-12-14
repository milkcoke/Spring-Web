package jpabook.jpashop.service.item;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    @Transactional(readOnly = false)
    public void saveItem(Item item) {
        itemRepository.save(item);
    }

    // 변경 감지에 의한 데이터 변경
    // @Transactional 에 의해 변경을 감지하고 update query 를 날려준다.


    @Transactional(readOnly = false)
    // 서비스 계층에 식별자(ID) 와 전달할 데이터를 명확하게 전달하라.
    // 복잡해질 경우 DTO 를 사용하길 권장한다.
    // TODO
    //  authorName 과 isbn 을 넘겨 업데이트 하고싶다면 어떻게 처리해야할까?
    public void updateItem(Long itemId, String name, int price, int stockQuantity) {
        // 식별자로 조회한 경우 영속성 컨텍스트를 갖는다.
        Item foundItem = itemRepository.findOneById(itemId);

        // 별도의 업데이트 메소드 사용 (domain 위치)
        foundItem.changeItemInfo(name, price, stockQuantity);
    }

    public List<Item> findItems() {
        return itemRepository.findAll();
    }

    public Item findOneById(Long id) {
        return itemRepository.findOneById(id);
    }
}
