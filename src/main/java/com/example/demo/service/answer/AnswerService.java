package com.example.demo.service.answer;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.*;
import org.springframework.stereotype.*;
import org.springframework.transaction.annotation.*;

import com.example.demo.domain.answer.*;
import com.example.demo.domain.question.*;
import com.example.demo.mapper.answer.*;

@Service
@Transactional(rollbackFor = Exception.class)
public class AnswerService {

	@Autowired
	private AnswerMapper mapper;
	
	public List<Answer> list(Integer questionId, Authentication authentication) {
		List<Answer> answers = mapper.selectAllByQuestionId(questionId);
		if (authentication != null) {
			for (Answer answer : answers) {
				answer.setIsAdmin(authentication.getAuthorities().stream()
	                    .anyMatch(auth -> auth.getAuthority().equals("admin")));
			}
		}
		return answers;
	}

	public Map<String, Object> add(Answer answer) {
		
		var res = new HashMap<String, Object>();
		mapper.updateQuestionAnswered(answer.getQuestionId());
		int cnt = mapper.insert(answer);
		if (cnt == 1) {
			res.put("message", "답변이 등록되었습니다.");
		} else {
			res.put("message", "답변이 등록되지 않았습니다.");
		}
		
		return res;
	}

	public Answer get(Integer id) {

		return mapper.selectById(id);
	}

	public Map<String, Object> update(Answer answer) {
		var res = new HashMap<String, Object>();
		
		int cnt = mapper.update(answer);
		if (cnt == 1) {
			res.put("message", "답변이 수정되었습니다.");
		} else {
			res.put("message", "답변이 수정되지 않았습니다.");
		}
		
		return res;
	}

	public Map<String, Object> remove(Integer id) {
		var res = new HashMap<String, Object>();
		
		int cnt = mapper.deleteById(id);
		if (cnt == 1) {
			res.put("message", "답변이 삭제되었습니다.");
		} else {
			res.put("message", "답변이 삭제되지 않았습니다.");
		}
		
		return res;
	}

}
