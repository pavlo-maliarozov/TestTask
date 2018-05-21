package com.test.task.contoller;

import com.test.task.model.UserInfo;
import com.test.task.service.UserInfoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.IsCollectionContaining.hasItem;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(UserInfoController.class)
public class UserInfoControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserInfoService userInfoServiceMock;

    @Test
    public void givenUserInfo_whenGetUserInfoId_thenReturnUserInfo() throws Exception {

        UserInfo userInfo = new UserInfo("Ivan",
                "Petrov",
                Date.valueOf("1999-01-01"),
                'M');

        long userId = 1L;

        userInfo.setUserId(userId);

        when(userInfoServiceMock.getUserInfoById(userId)).thenReturn(userInfo);

        mockMvc.perform(get("/view/{user_id}", userId))
                .andExpect(status().isOk())
                .andExpect(view().name("view"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/view.jsp"))
                .andExpect(model()
                        .attribute("user", hasProperty("userId", is(new Long("1")))))
                .andExpect(model()
                        .attribute("user", hasProperty("firstName", is("Ivan"))))
                .andExpect(model()
                        .attribute("user", hasProperty("lastName", is("Petrov"))))
                .andExpect(model()
                        .attribute("user", hasProperty("birthday",
                                is(Date.valueOf("1999-01-01")))))
                .andExpect(model()
                        .attribute("user", hasProperty("gender", is('M'))));

        verify(userInfoServiceMock, times(1)).getUserInfoById(userId);
        verifyNoMoreInteractions(userInfoServiceMock);
    }

    @Test
    public void givenUserInfos_whenGetUserInfos_thenReturnAllUserInfos() throws Exception {

        UserInfo userInfo1 = new UserInfo(
                "Ivan",
                "Ivanov",
                Date.valueOf("1991-03-02"),
                'M'
        );

        UserInfo userInfo2 = new UserInfo(
                "Maria",
                "Petrova",
                Date.valueOf("1996-04-03"),
                'F'
        );

        UserInfo userInfo3 = new UserInfo(
                "Vladimir",
                "Petrov",
                Date.valueOf("1990-05-07"),
                'M'
        );

        List<UserInfo> userInfoList = Arrays.asList(userInfo1, userInfo2, userInfo3);

        given(userInfoServiceMock.findAll()).willReturn(userInfoList);

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/index.jsp"))
                .andExpect(model().attribute("userList", hasSize(3)))
                .andExpect(model().attribute("userList", hasItem(
                        allOf(
                                hasProperty("firstName", is("Ivan")),
                                hasProperty("lastName", is("Ivanov")),
                                hasProperty("birthday", is(Date.valueOf("1991-03-02"))),
                                hasProperty("gender", is('M'))
                        )
                    )
                ))
                .andExpect(model().attribute("userList", hasItem(
                        allOf(
                                hasProperty("firstName", is("Maria")),
                                hasProperty("lastName", is("Petrova")),
                                hasProperty("birthday", is(Date.valueOf("1996-04-03"))),
                                hasProperty("gender", is('F'))
                        )
                    )
                ))
                .andExpect(model().attribute("userList", hasItem(
                        allOf(
                                hasProperty("firstName", is("Vladimir")),
                                hasProperty("lastName", is("Petrov")),
                                hasProperty("birthday", is(Date.valueOf("1990-05-07"))),
                                hasProperty("gender", is('M'))
                        )
                    )
                ));

        verify(userInfoServiceMock, times(1)).findAll();
        verifyNoMoreInteractions(userInfoServiceMock);

    }
}