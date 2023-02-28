package com.epam.esm.service.impl;

/*

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void getAll() {
        List<User> expected = Arrays.asList(FIRST_TEST_USER, SECOND_TEST_USER);
        when(userRepository.getAll(PAGE)).thenReturn(expected);
        List<User> actual = userService.getAll(PAGE_NUM, PAGE_SIZE);

        assertEquals(expected, actual);

    }

    @Test
    void getById() {
        when(userRepository.getById(TEST_ID)).thenReturn(Optional.of(FIRST_TEST_USER));
        User actual = userService.getById(TEST_ID);
        assertEquals(FIRST_TEST_USER, actual);
    }

    @Test
    void getByIdShouldThrowEntityNotFoundException() {
        when(userRepository.getById(NOT_EXIST_ID)).thenThrow(new EntityNotFoundException());
        assertThrows(EntityNotFoundException.class, () -> userService.getById(NOT_EXIST_ID));
    }
}*/
