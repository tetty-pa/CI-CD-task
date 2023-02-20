package com.epam.esm.service.impl;

/*

@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    private OrderDaoImpl orderDao;

    @Mock
    private UserDaoImpl userDao;

    @Mock
    private GiftCertificateDaoImpl giftCertificateDao;

    @InjectMocks
    private OrderServiceImpl orderService;


    @Test
    void getAllByUserId() {
        List<Order> expected = Arrays.asList(FIRST_TEST_ORDER, SECOND_TEST_ORDER);
        when(orderDao.getAllByUserId(TEST_ID, PAGE)).thenReturn(expected);
        when(userDao.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_USER));

        List<Order> actual = orderService.getAllByUserId(TEST_ID, PAGE_NUM, PAGE_SIZE);

        assertEquals(expected, actual);
    }

    @Test
    void getAllByUserIdShouldThrowEntityNotFoundException() {
        when(userDao.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> orderService.getAllByUserId(NOT_EXIST_ID, PAGE_NUM, PAGE_SIZE));
    }

    @Test
    void create() {
        when(userDao.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_USER));
        when(giftCertificateDao.getById(3)).thenReturn(Optional.of(THIRD_TEST_GIFT_CERTIFICATE));
        when(orderDao.create(THIRD_TEST_ORDER)).thenReturn(THIRD_TEST_ORDER);

        Order actual = orderService.create(TEST_ID, 3);
        assertEquals(THIRD_TEST_ORDER, actual);
    }

    @Test
    void createShouldThrowEntityNotFoundException() {
        when(userDao.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());

        assertThrows(EntityNotFoundException.class, () -> orderService.create(NOT_EXIST_ID, NOT_EXIST_ID));
    }

    @Test
    void getById() {
        when(orderDao.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_ORDER));
        Order actual = orderService.getById(TEST_ID);
        assertEquals(FIRST_TEST_ORDER, actual);
    }

    @Test
    void getByIdShouldThrowEntityNotFoundException() {
        when(orderDao.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> orderService.getById(NOT_EXIST_ID));
    }
}*/
