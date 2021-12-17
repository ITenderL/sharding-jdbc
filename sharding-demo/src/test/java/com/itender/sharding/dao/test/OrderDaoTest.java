package com.itender.sharding.dao.test;

import com.itender.ShardingApplication;
import com.itender.sharding.dao.DictDao;
import com.itender.sharding.dao.OrderDao;
import com.itender.sharding.dao.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: ITender
 * @CreateTime: 2021-12-16 11:28
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = {ShardingApplication.class})
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private DictDao dictDao;

    /**
     * 分表插入
     */
    // @Test
    // public void testInsertOrder() {
    //     for (int i = 1; i < 20; i++) {
    //         orderDao.insertOrder(new BigDecimal(i), 1L, "SUCCESS");
    //     }
    // }
    @Test
    public void testSelectOrderbyIds() {
        List<Long> ids = new ArrayList<>();
        ids.add(611607906873245696L);
        ids.add(611607906965520384L);

        List<Map> maps = orderDao.selectOrderbyIds(ids);
        System.out.println(maps);
    }

    /**
     * 分库插入
     */
    @Test
    public void testInsertOrder() {
        for (int i = 0; i < 10; i++) {
            orderDao.insertOrder(new BigDecimal((i + 1) * 5), 1L, "WAIT_PAY");
        }
        for (int i = 0; i < 10; i++) {
            orderDao.insertOrder(new BigDecimal((i + 1) * 10), 2L, "WAIT_PAY");
        }
    }

    @Test
    public void testInsertUser() {
        for (int i = 0; i < 10; i++) {
            Long id = i + 1L;
            userDao.insertUser(id, "姓名" + id);
        }
    }

    @Test
    public void testSelectUserbyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserbyIds(userIds);
        System.out.println(users);
    }

    @Test
    public void testSelectUserInfobyIds() {
        List<Long> userIds = new ArrayList<>();
        userIds.add(1L);
        userIds.add(2L);
        List<Map> users = userDao.selectUserInfobyIds(userIds);
        JSONArray jsonUsers = new JSONArray(users);
        System.out.println(jsonUsers);
    }

    @Test
    public void testInsertDict() {
        dictDao.insertDict(1L, "user_type", "0", "管理员");
        dictDao.insertDict(2L, "user_type", "1", "操作员");
    }

    @Test
    public void testDeleteDict() {
        dictDao.deleteDict(1L);
        dictDao.deleteDict(2L);
    }

    @Test
    public void test01() {
        int[] array = {8, 6, 5, 3, 6, 4, 6, 6, 6};
        List<Integer> list = Arrays.stream(array).boxed().collect(Collectors.toList());
        //groupingBy分组
        Map<Integer, Long> map = list.stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));
        for (Integer key : map.keySet()) {
            if (map.get(key) > (long) (list.size() / 2)) {
                System.out.println(key);
            }

        }
        // System.out.println(5/2);
    }

    @Test
    public void findKth() {
        int[] a = {5, 9, 3, 6, 2};
        Arrays.sort(a);
        for (int i : a) {
            System.out.print(i + ", ");
        }
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode firstNode = new ListNode(1);
        ListNode secondNode = new ListNode(2);
        ListNode thirdNode = new ListNode(3);
        head.val = 1;
        head.next = firstNode;
        firstNode.next = secondNode;
        secondNode.next = thirdNode;
        a(head);
    }

    public static void a(ListNode l1) {
        System.out.println("secondNode 的值为：" + l1.next.next.val);
    }

}
