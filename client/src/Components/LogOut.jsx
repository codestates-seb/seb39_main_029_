import { useNavigate } from "react-router-dom";
import styled from "styled-components";
import ColorButton from "../Assets/ColorBtn";
import "../index";
import axios from "axios";

function LogOut() {
  const navigate = useNavigate();
  const token = localStorage.getItem("accessToken");

  const handleLogout = () => {
    localStorage.setItem("accessToken", "");
    navigate("/");
  };

  return (
    <Container>
      <LogOutWrapper>
        <div className="ad">“Are you sure you want to log out?”</div>
        <div className="btn">
          <ColorButton mode={"BLUE"} text={"Logout"} onClick={handleLogout} />
          <ColorButton
            mode={"GREY"}
            text={"Return"}
            onClick={() => {
              navigate("/info");
            }}
          />
        </div>
      </LogOutWrapper>
    </Container>
  );
}

const Container = styled.div`
  width: 100;
  height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background-color: var(--theme-background-gray);
`;
const LogOutWrapper = styled.div`
  width: 400px;
  height: 400px;
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 5px;
  box-shadow: 0 10px 24px hsla(0, 0%, 0%, 0.05),
    0 20px 48px hsla(0, 0%, 0%, 0.05), 0 1px 4px hsla(0, 0%, 0%, 0.1);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  align-content: center;
  .ad {
    margin: 80px;
    width: 300px;
    height: 100px;
    display: flex;
    justify-content: center;
    align-items: center;
    font-size: var(--name-font);
    font-family: var(--serif);
  }
  .btn {
    margin: 20px;
    display: flex;
    width: 200px;
    justify-content: space-around;
  }
`;

export default LogOut;
