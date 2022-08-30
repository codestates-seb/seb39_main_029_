import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import "../index";
import Img1 from "../Assets/Imgs/signup-1.jsx";
import Img2 from "../Assets/Imgs/signup-2.jsx";
import Img3 from "../Assets/Imgs/signup-3.jsx";
import Img4 from "../Assets/Imgs/signup-4.jsx";
import Google from "../Assets/Imgs/google";
import Github from "../Assets/Imgs/github";
import { useState } from "react";
import axios from "axios";

function SignUp() {
  const navigate = useNavigate();
  const [nickname, setNickname] = useState("");
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  console.log(nickname);
  console.log(email);
  console.log(password);

  const SignUpForm = {
    nickName: nickname,
    password: password,
    email: email,
  };

  const SignUpHandler = () => {
    axios
      .post(
        "http://ec2-15-165-204-159.ap-northeast-2.compute.amazonaws.com:8080/v1/members/join",
        SignUpForm
      )
      .then((res) => {
        console.log(res);
      });
  };

  return (
    <Container>
      <Ad>
        <h1>Join the Stack Overflow community</h1>
        <p>
          <Img1 className="img" />
          Get unstuck — ask a question
        </p>
        <p>
          <Img2 className="img" />
          Unlock new privileges like voting and commenting
        </p>
        <p>
          <Img3 className="img" />
          Save your favorite tags
        </p>
        <p>
          <Img4 className="img" />
          Earn reputation and badges
        </p>
        <p className="explain">
          Collaborate and share knowledge with a private group for FREE.
        </p>
      </Ad>
      <Wrapper>
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
          <label for="id">Display name</label>
          <input
            type="text"
            id="id"
            onChange={(e) => {
              setNickname(e.target.value);
            }}
          />
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
          <p>
            Passwords must contain at least eight characters, including at least
            1 letter and 1 number.
          </p>
          <button
            onClick={() => {
              SignUpHandler();
              navigate("/");
            }}
          >
            Sign up
          </button>
          <span>
            Already have an account?{" "}
            <span
              className="toLogin"
              onClick={() => {
                navigate("/login");
              }}
            >
              Log in
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
const Ad = styled.div`
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  justify-content: center;
  padding: 0;
  margin: 0 40px 0 0;
  width: 100;
  height: 100;
  > h1 {
    margin: 0 0 10px 0;
    font-size: var(--header-font);
    font-weight: normal;
  }
  > p {
    display: flex;
    font-size: var(--text-font);
    margin: 10px;
  }
  .img {
    fill: var(--theme-blue);
    margin: 0 10px 0 0;
  }
  .explain {
    font-size: var(--normal-font);
    color: var(--font-color-gray);
  }
`;
const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  justify-content: center;
  width: 280px;
  height: 100;
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
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    font-size: var(--normal-font);
    font-weight: bold;
    color: var(--theme-white);
    background-color: var(--theme-blue);
    cursor: pointer;

    :hover {
      filter: brightness(90%);
    }
  }
  > p {
    font-size: var(--small-font);
    color: var(--font-color-gray);
    cursor: default;
  }
  > span {
    margin-top: 20px;
    font-size: var(--small-font);
    cursor: default;
  }
  .toLogin {
    color: var(--font-color-blue);
    cursor: pointer;
  }
`;

export default SignUp;
