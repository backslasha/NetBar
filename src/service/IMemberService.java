package service;

import com.bean.Member;

public interface IMemberService {
	void registerMember(Member member);
	Member loginMember(String username,String password);
}
