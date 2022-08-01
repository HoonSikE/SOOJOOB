package com.D210.soojoobback.controller;

import com.D210.soojoobback.JwtTokenProvider;
import com.D210.soojoobback.UserDetailsImpl;
import com.D210.soojoobback.dto.plogging.PostPloggingReqDto;
import com.D210.soojoobback.dto.user.*;
import com.D210.soojoobback.entity.Plogging;
import com.D210.soojoobback.entity.User;
import com.D210.soojoobback.exception.CustomErrorException;
import com.D210.soojoobback.repository.PloggingRepository;
import com.D210.soojoobback.service.PloggingService;
import com.D210.soojoobback.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/plogging")
public class PloggingController {

    private final PloggingService ploggingService;
    @Autowired
    private PloggingRepository ploggingRepository;

    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserService userService;

//    @PostMapping("")
//    @ResponseBody
//    public ResponseDto createPlogging(
//            @RequestBody PostPloggingReqDto postPloggingReqDto,
//        @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        Long userId = userDetails.getUser().getId();
//        if (ploggingService.save(postPloggingReqDto, ) != null) {
////            Plogging plogging = ploggingService.save(postPloggingReqDto, userId);
//            return new ResponseDto(201L, "플로깅 생성에 성공했습니다 !","");
//        }
//        return new ResponseDto(500L, "플로깅 생성에 실패하였습니다 ...", "");
//    }

    @PostMapping("")
    public ResponseDto createPlogging(
            @AuthenticationPrincipal UserDetailsImpl userDetailsImpl,
            @RequestBody PostPloggingReqDto requestDto
    ) {
        checkLogin(userDetailsImpl);
        User user = userDetailsImpl.getUser();
        ploggingService.save(requestDto, user);
        return new ResponseDto(201L,"플로깅 생성에 성공했습니다 !", "");
    }

    private void checkLogin(
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        System.out.println(userDetails);
        if (userDetails == null) {
            throw new CustomErrorException("로그인이 필요합니다.2");
        }
    }
//
//    @PostMapping("/login")
//    @ResponseBody
//    public ResponseDto login(
//            @RequestBody LoginUserDto loginUserDto,
//            HttpServletResponse response) {
//        User user = userService.login(loginUserDto);
//        String checkEmail = user.getEmail();
//
//        String token = jwtTokenProvider.createToken(checkEmail);
//        response.setHeader("X-AUTH-TOKEN", token);
//
//        Cookie cookie = new Cookie("X-AUTH-TOKEN", token);
//        cookie.setPath("/");
//        cookie.setHttpOnly(true);
//        cookie.setSecure(true);
//        response.addCookie(cookie);
//
//        LoginResponseDto loginResponseDto = new LoginResponseDto();
//        LoginDetailResponseDto loginDetailResponseDto = userService.toSetLoginDetailResponse(user);
//        loginResponseDto.setUser(loginDetailResponseDto);
//        loginResponseDto.setJwtToken(token);
//
//        return new ResponseDto(200L, "로그인에 성공했습니다", loginResponseDto);
//    }
//
//    @DeleteMapping("/delete/{ploggingId}")
//    @ResponseBody
//    public ResponseDto deletePlogging(
//            @AuthenticationPrincipal UserDetailsImpl userDetails,
//            @PathVariable Long ploggingId)
//     {
//         if (ploggingService.findByPloggingId(ploggingId).getPloggingUser() == userDetails.getUser()){
//            return new ResponseDto(204L, "플로깅 기록이 삭제되었습니다", "");
//        }
//        return new ResponseDto(500L, "플로깅 기록 삭제 실패", "");
//    }

    @DeleteMapping("/delete/{plogging_id}")
    public ResponseDto deletePlogging(
            @AuthenticationPrincipal UserDetails userDetails,
            @PathVariable("plogging_id") Long ploggingId
    ) {
        User user = userService.userFromUserDetails(userDetails);
        ploggingService.deletePlogging(ploggingId,user);
        return new ResponseDto(204L, "플로깅 기록 삭제에 성공하였습니다.", "");
    }
//
//    @GetMapping("")
//    @ResponseBody
//    public ResponseDto userInfoDetails(
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        System.out.println();
//        checkLogin(userDetails);
//        List<UserInfoDetailsDto> userInfoDetailsDto = userService.detailsUserInfo(userDetails);
//
//        return new ResponseDto(200L, "회원 정보를 전송했습니다", userInfoDetailsDto);
//
//    }
//
//
//    @PutMapping("/update")
//    @ResponseBody
//    public ResponseDto editUserInfo(
//            @RequestBody EditUserRequestDto editUserInfoDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        checkLogin(userDetails);
//        EditUserResponseDto editUserResponseDto = userService.editUserInfo(editUserInfoDto, userDetails);
//
//        return new ResponseDto(200L, "회원 정보를 수정했습니다", editUserResponseDto);
//
//    }
//
//    @PutMapping("/change/pw")
//    @ResponseBody
//    public ResponseDto editUserPassword(
//            @RequestBody EditPasswordRequestDto editPasswordRequestDto,
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        checkLogin(userDetails);
//        userService.editUserPassword(editPasswordRequestDto, userDetails);
//
//        return new ResponseDto(200L, "비밀 번호를 수정했습니다", "");
//    }
//
//
//    private void checkLogin(
//            @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        System.out.println(userDetails);
//        if (userDetails == null) {
//            throw new CustomErrorException("로그인이 필요합니다.2");
//        }
//    }
//
//    @GetMapping("/nickname-check")
//    public ResponseDto nickNameCheck(
//            String username
//    ) {
//        userService.nicknameCheck(username);
//        return new ResponseDto(200L, "사용 가능한 닉네임입니다 !", "");
//
//    }
//
//
//    @GetMapping("/email-check")
//    public ResponseDto emailCheck(
//            String email
//    ) {
//        userService.emailCheck(email);
//        return new ResponseDto(200L, "사용 가능한 이메일입니다 !", "");
//
//    }
//
//
//    // id(pk)로 유저정보 조회
//    @GetMapping("/{id}")
//    public ResponseDto findById(@PathVariable Long id){
//
//        UserDTO userDTO = userService.findById(id);
//        return new ResponseDto(200L, "ID에 해당하는 유저정보를 전송했습니다 !", userDTO);
//    }
}
