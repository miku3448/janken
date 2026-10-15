package oit.is.z3696.kaizi.janken.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class JankenAuthConfiguration {
  // 認可処理
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/janken").authenticated() // /sample3/以下は認証済みであること
            .anyRequest().permitAll());// 上記以外は全員アクセス可能
    return http.build();
  }

  // 認証処理
  @Bean
  public InMemoryUserDetailsManager userDetailsService() {
    UserDetails user1 = User.withUsername("user1")
        .password("{bcrypt}$2y$10$iBQSfP5RTvs4xqKI25GOheTk40zJN.MWPWq5AuBg3P11BWGsHY0ym").roles("USER").build();
    UserDetails user2 = User.withUsername("user2")
        .password("{bcrypt}$2y$10$iBQSfP5RTvs4xqKI25GOheTk40zJN.MWPWq5AuBg3P11BWGsHY0ym").roles("USER").build();

    return new InMemoryUserDetailsManager(user1, user2);
  }

}
