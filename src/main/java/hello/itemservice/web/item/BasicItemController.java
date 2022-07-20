package hello.itemservice.web.item;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;

@Controller
@RequiredArgsConstructor
@RequestMapping(value = "/basic/items")
public class BasicItemController {

    private final ItemRepository itemRepository;

    @PostConstruct
    public void init() {
        itemRepository.save(new Item("testA", 10000, 10));
        itemRepository.save(new Item("testB", 20000, 20));
    }

    @GetMapping
    public String getItemList(Model model) {
        model.addAttribute("items", itemRepository.findAll());
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String getItem(@PathVariable Long itemId, Model model) {
        model.addAttribute("item", itemRepository.findById(itemId));
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm() {
        return "basic/addForm";
    }

    @PostMapping("/add")
    public String addItem(@ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("addStatus", true);
        return "redirect:/basic/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item savedItem = itemRepository.findById(itemId);
        model.addAttribute("item", savedItem);
        return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @ModelAttribute Item item, RedirectAttributes redirectAttributes) {
        itemRepository.update(itemId, item);
        redirectAttributes.addAttribute("itemId", item.getId());
        redirectAttributes.addAttribute("editStatus", true);
        return "redirect:/basic/items/{itemId}";
    }
}
