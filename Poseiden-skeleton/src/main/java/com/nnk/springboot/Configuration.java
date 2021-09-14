package com.nnk.springboot;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Configuration extends WebSecurityConfigurerAdapter {

    /**
     * {@inheritDoc}
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorize -> {
                    authorize
                            .antMatchers("/")
                            .anonymous();
                })
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .and()
                .httpBasic()
                .and()
                .oauth2Login()
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .accessDeniedPage("/app/error")
                .and()
                .logout()
                .logoutSuccessUrl("/");
    }

    /**
     * Password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}

