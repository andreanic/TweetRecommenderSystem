import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LoginRepositoryService } from 'app/repository/login/login-repository.service';

@Injectable()
export class LoginService {

  private username: string;
  private password: string;

  constructor(private loginRepository: LoginRepositoryService) { }


  public login(): Observable<any>{
    return this.loginRepository.login({
      username: this.username,
      password: this.password,
    });
  }

    /**
     * Getter $username
     * @return {string}
     */
	public get $username(): string {
		return this.username;
	}

    /**
     * Getter $password
     * @return {string}
     */
	public get $password(): string {
		return this.password;
	}

    /**
     * Setter $username
     * @param {string} value
     */
	public set $username(value: string) {
		this.username = value;
	}

    /**
     * Setter $password
     * @param {string} value
     */
	public set $password(value: string) {
		this.password = value;
	}
  


}
