import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import "../index";
import Logo from "../Assets/Imgs/stackoverflow-only";
import Google from "../Assets/Imgs/google";
import Github from "../Assets/Imgs/github";
import axios from "axios";
import { useState } from "react";
import { useRecoilState } from "recoil";
import { UserState } from "../States/UserState";

function LogIn() {
  const navigate = useNavigate();
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [user, setUser] = useRecoilState(UserState);

  console.log(email);
  console.log(password);

  const userLoginForm = {
    email: email,
    password: password,
  };

  const SigninHandler = () => {
    axios
      .post(
        "http://ec2-15-165-204-159.ap-northeast-2.compute.amazonaws.com:8080/v1/members/login",
        userLoginForm
      )
      .then((res) => {
        setUser(res.data);
        console.log(res.data);
      });
  };

  return (
    <Container>
      <Wrapper>
        <Img>
          <Logo />
        </Img>
        <SocialWrapper>
          <button>
            <Google className="icon" />
            Sign up with Google
          </button>
          <button className="github">
            <Github className="icon" />
            Sign up with Github
          </button>
        </SocialWrapper>
        <SignupWrapper>
          <label for="email">Email</label>
          <input
            type="email"
            id="email"
            onChange={(e) => {
              setEmail(e.target.value);
            }}
          />
          <label for="pw">Password</label>
          <input
            type="password"
            id="pw"
            onChange={(e) => {
              setPassword(e.target.value);
            }}
          />
          <button
            onClick={() => {
              SigninHandler();
              navigate("/home");
            }}
          >
            Log in
          </button>
          <span>
            Don’t have an account?{" "}
            <span
              className="toSignup"
              onClick={() => {
                navigate("/signup");
              }}
            >
              Sign up
            </span>
          </span>
        </SignupWrapper>
      </Wrapper>
    </Container>
  );
}

const Container = styled.div`
  margin: 0;
  padding: 0;
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: var(--theme-background-gray);
  font-family: var(--sans-serif);
`;
const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 280px;
  height: 100;
`;
const Img = styled.div`
  align-self: center;
  margin: 0 0 30px 0;
`;
const SocialWrapper = styled.div`
  display: flex;
  flex-direction: column;
  > button {
    display: flex;
    justify-content: center;
    align-items: center;
    padding: 8px;
    margin: 0 0 10px 0;
    font-size: var(--normal-font);
    background-color: var(--theme-white);
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    cursor: pointer;

    :hover {
      filter: brightness(90%);
    }
  }
  .icon {
    margin: 0 10px 0 0;
  }
  .github {
    background-color: var(--theme-github-black);
    color: var(--theme-white);
  }
`;
const SignupWrapper = styled.div`
  display: flex;
  flex-direction: column;
  background-color: var(--theme-white);
  padding: 24px;
  border-radius: 10px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  > label {
    font-size: var(--text-font);
    font-weight: bold;
  }
  > input {
    margin: 10px 0 20px 0;
    padding: 6px;
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
  }
  > button {
    margin-top: 20px;
    padding: 8px;
    font-size: var(--normal-font);
    font-weight: bold;
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    color: var(--theme-white);
    background-color: var(--theme-blue);
    cursor: pointer;

    :hover {
      filter: brightness(90%);
    }
  }
  > span {
    margin-top: 20px;
    font-size: var(--small-font);
    cursor: default;
  }
  .toSignup {
    cursor: pointer;
    color: var(--font-color-blue);
  }
`;

export default LogIn;
