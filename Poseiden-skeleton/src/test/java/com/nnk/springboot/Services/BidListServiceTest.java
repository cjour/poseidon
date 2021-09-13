package com.nnk.springboot.Services;


import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.repositories.BidListRepository;
import com.nnk.springboot.services.BidListService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BidListServiceTest {

    @MockBean
    BidListService bidListService;

    private List<BidList> list;

    @BeforeEach
    public void setUp() {
        this.list = new ArrayList<>();
        list.add(new BidList(1, "account", "type", 1d, 2d));
        list.add(new BidList(2, "account2", "type2", 3d, 4d));
    }

    @Test
    public void serviceShouldReturnAllBidList(){
        Mockito.when(bidListService.findAll()).thenReturn(list);
        System.out.println(list.size());
        BidList firstIndexOfList = list.get(0);
        BidList secondIndexOfList = list.get(1);

        assertEquals(1, (int) firstIndexOfList.getId());
        assertEquals(2, (int) secondIndexOfList.getId());
        assertEquals(2, list.size());

    }

}
