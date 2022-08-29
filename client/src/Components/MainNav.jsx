import styled from "styled-components";
import Button from "../Assets/Button";
import Stackoverflowlogo from "../Assets/Imgs/stackoverflow.jsx";
import { useNavigate } from "react-router-dom";
import ColorButton from "../Assets/ColorBtn";

function MainNav() {
  const navigate = useNavigate();
  return (
    <Container>
      <Line />
      <Navbody>
        <Stackoverflowlogo
          className="logo"
          alt="react"
          cursor="pointer"
          onClick={() => {
            navigate("/");
          }}
        />
        <div className="btnwrapper">
          <ColorButton
            mode="GREY"
            text="Log in"
            onClick={() => {
              navigate("/login");
            }}
          />
          <ColorButton
            mode="BLUE"
            text="Sign up"
            onClick={() => {
              navigate("/signup");
            }}
          />
        </div>
      </Navbody>
    </Container>
  );
}

const Container = styled.div`
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 50px;
  background-color: #f8f9f9;
  box-shadow: 0 1px 2px lightgray;
`;
const Line = styled.div`
  width: 100%;
  height: 3px;
  background-color: #f48225;
`;
const Navbody = styled.div`
  display: flex;
  align-items: center;
  justify-content: space-between;
  width: 100%;
  height: 47px;
  .btnwrapper {
    display: flex;
    margin-right: 20px;
  }
  .btnwrapper > button {
    margin-left: 10px;
  }
  .loginpbtn {
    margin-right: 10px;
  }
  .logo {
    width: 158px;
    height: 30px;
    margin-left: 56px;
  }
`;

export default MainNav;
