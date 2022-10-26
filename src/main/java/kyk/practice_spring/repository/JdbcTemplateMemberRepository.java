package kyk.practice_spring.repository;


import kyk.practice_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

// 중복되는 jdbc의 작업을 없애준다.
public class JdbcTemplateMemberRepository implements MemberRepository {

    private final JdbcTemplate jdbcTemplate; // jdbcTemplate을 사용해야 함

    // 만약 생성자가 하나이면 @Autowired 생략 가능
    public JdbcTemplateMemberRepository(DataSource dataSource) {  // 그냥 인젝션으로 받는 것이 아니므로 데이터 소스를 인젝션 받아야함
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Member save(Member member) {
        // 쿼리문 필요없이 SimpleJdbcInsert을 활용하여 작성
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("member").usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", member.getName());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        member.setId(key.longValue());

        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // jdbc 연결의 많은 코드량이 단 두줄로 단축됨 (jdbcTemplate 라이브러리)
        List<Member> result = jdbcTemplate.query("select * from member where id = ?", memberRowMapper(), id);
        return result.stream().findAny();
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = jdbcTemplate.query("select * from member where name = ?", memberRowMapper(), name);
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        return jdbcTemplate.query("select *from member", memberRowMapper());
    }

    private RowMapper<Member> memberRowMapper() {  // 맴버 객체 생성
        // return new RowMapper<Member>() {
        // @Override를 람다 스타일로 변경
        return (rs, rowNum) -> { // 결과를 맴버의 객체로 돌려줌

            Member member = new Member();
            member.setId(rs.getLong("id"));
            member.setName(rs.getString("name"));

            return member;
        };
    }

}


