import styled from "styled-components";
import { useState } from "react";
import axios from "axios";
import ColorButton from "../Assets/ColorBtn";

function InfoEditModal({ userInfo, setUserInfo, setIsModal }) {
  const token = localStorage.getItem("accessToken");
  const [nickName, setNickName] = useState("");
  const [location, setLocation] = useState("");
  const [title, setTitle] = useState("");
  const InfoEdit = () => {
    axios
      .patch(
        `http://seb039pre029.ga:8080/v1/members/update/${userInfo.memberid}`,
        editForm,
        {
          headers: { Authorization: token },
        }
      )
      .then((res) => {
        setUserInfo((prev) => ({ ...prev, ...editForm }));
        console.log(res.data);
      });
  };

  const editForm = {
    nickName: nickName,
    selfId: null,
    location: location,
    title: title,
  };
  return (
    <Container>
      <div>
        nickName :{" "}
        <input
          placeholder={userInfo.nickName}
          onChange={(e) => {
            setNickName(e.target.value);
          }}
        ></input>
      </div>
      <div>
        location :{" "}
        <input
          placeholder={userInfo.location}
          onChange={(e) => {
            setLocation(e.target.value);
          }}
        ></input>
      </div>
      <div>
        title :{" "}
        <input
          placeholder={userInfo.title}
          onChange={(e) => {
            setTitle(e.target.value);
          }}
        ></input>
      </div>
      <ButtonWrapper>
        <ColorButton
          mode="BLUE"
          text="Save profile"
          onClick={() => {
            InfoEdit();
          }}
        ></ColorButton>
        <ColorButton
          mode="GREY"
          text="Cancel"
          onClick={() => {
            setIsModal(false);
          }}
        ></ColorButton>
      </ButtonWrapper>
    </Container>
  );
}

const Container = styled.div`
  position: absolute;
  top: 50%;
  left: 40%;
  background-color: var(--theme-ornage-light);
  width: 500px;
  height: 500px;
`;

const ButtonWrapper = styled.div`
  display: flex;
`;

export default InfoEditModal;
