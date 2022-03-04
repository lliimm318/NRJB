package com.nrjb.user.service;

import com.nrjb.exception.exceptions.InvalidTokenException;
import com.nrjb.exception.exceptions.UserAlreadyException;
import com.nrjb.exception.exceptions.UserNotFoundException;
import com.nrjb.security.TokenProvider;
import com.nrjb.user.entity.RefreshToken;
import com.nrjb.user.entity.RefreshTokenRepository;
import com.nrjb.user.entity.User;
import com.nrjb.user.entity.UserRepository;
import com.nrjb.user.payload.request.JoinRequest;
import com.nrjb.user.payload.request.SignInRequest;
import com.nrjb.user.payload.response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final RefreshTokenRepository refreshTokenRepository;

    private final TokenProvider tokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Value("${auth.jwt.exp.refresh}")
    private Long refreshTokenTime;

    @Value("${auth.jwt.prefix}")
    private String tokenType;


    @Override
    public void Join(JoinRequest joinRequest) {
        userRepository.findById(joinRequest.getId())
        .ifPresent(user -> {
            throw new UserAlreadyException();
        });

        User user = User.builder()
                .id(joinRequest.getId())
                .name(joinRequest.getName())
                .password(passwordEncoder.encode(joinRequest.getPassword()))
                .build();

        userRepository.save(user);
    }

    @Override
    public TokenResponse SignIn(SignInRequest signInRequest) {
        userRepository.findById(signInRequest.getId())
                .filter(u -> passwordEncoder.matches(signInRequest.getPassword(), u.getPassword()))
                .orElseThrow(UserNotFoundException::new);

        RefreshToken refreshToken = RefreshToken.builder()
                .userId(signInRequest.getId())
                .refreshToken(tokenProvider.generateRefreshToken(signInRequest.getId()))
                .ttl(refreshTokenTime)
                .build();

        refreshTokenRepository.save(refreshToken);

        return TokenResponse.builder()
                .accessToken(tokenProvider.generateAccessToken(signInRequest.getId()))
                .refreshToken(tokenProvider.generateRefreshToken(signInRequest.getPassword()))
                .build();
    }

    @Override
    public TokenResponse tokenRefresh(String token) {
        return refreshTokenRepository.findByRefreshToken(token)
                .map(refreshToken -> {
                    String accessToken = tokenProvider.generateAccessToken(refreshToken.getUserId());
                    return refreshToken.update(accessToken, refreshTokenTime);
                })
                .map(refreshTokenRepository::save)
                .map(refreshToken -> {
                    String accessToken = tokenProvider.generateAccessToken(refreshToken.getUserId());
                    return new TokenResponse(accessToken, refreshToken.getRefreshToken());
                })
                .orElseThrow(InvalidTokenException::new);
    }

}
