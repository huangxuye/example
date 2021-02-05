/*
package com.wxuy.example.controller;

import com.wxuy.example.entity.BaseRequest;
import com.wxuy.example.entity.User;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@Api(tags = {"Template"})
@RestController
@RequestMapping(value = "/api/template")
@Validated
//验证请求参数(Path Variables 和 Request Parameters)
//一定一定不要忘记在类上加上 Validated 注解了，这个参数可以告诉 Spring 去校验方法参数。
public class TemplateController {

	@Value("${wuhan2020}")
	String wuhan2020;
	*/
/**
	 * 以下为伪代码
	 * @return
	 *//*

	//我们在需要验证的参数上加上了@Valid注解，如果验证失败，它将抛出MethodArgumentNotValidException。
	@GetMapping("/users")
	//@RequestMapping(value="/users",method=RequestMethod.GET)
	public ResponseEntity<List<User>> getAllUsers() {
//		return userRepository.findAll();
		return null;
	}

	@PostMapping("/users")
	//@RequestMapping(value="/users",method=RequestMethod.POST)
	public ResponseEntity<User> createUser(@Valid @RequestBody BaseRequest baseRequest) {
//		return userRespository.save(user);
		return null;
	}

	@PutMapping("/users/{userId}")
	//@RequestMapping(value="/users/{userId}",method=RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable(value = "userId") Long userId,
										   @Valid @RequestBody BaseRequest baseRequest) {
//		......
		return null;
	}

	@DeleteMapping("/users/{userId}")
	//@RequestMapping(value="/users/{userId}",method=RequestMethod.DELETE)
	public ResponseEntity deleteUser(@PathVariable(value = "userId") Long userId){
// 		......
		return null;
	}

	@PatchMapping("/profile")
	public ResponseEntity updateStudent(@RequestBody BaseRequest baseRequest) {
//		studentRepository.updateDetail(studentUpdateRequest);
//		return ResponseEntity.ok().build();
		return null;
	}

	*/
/**
	 * @PathVariable用于获取路径参数，@RequestParam用于获取查询参数。
	 * @param klassId
	 * @param type
	 * @return
	 *//*

	@GetMapping("/klasses/{klassId}/teachers")
	public List<User> getKlassRelatedTeachers(
			@PathVariable("klassId") Long klassId,
			@RequestParam(value = "type", required = false) String type ) {
//		... 模拟请求 /klasses/{123456}/teachers?type=web  得到 klassId=123456,type=web
		return null;
	}

	*/
/**
	 *
	 * @param user
	 * @return
	 *//*

	@PostMapping("/sign-up")
	public ResponseEntity signUp(@RequestBody @Valid User user) {
//		userService.save(user);
//		return ResponseEntity.ok().build();
		return null;
	}

	@GetMapping("/person/{id}")
	public ResponseEntity<Integer> getPersonByID(@Valid @PathVariable("id") @Max(value = 5,message = "超过 id 的范围了") Integer id) {
		return ResponseEntity.ok().body(id);
	}
}
*/
