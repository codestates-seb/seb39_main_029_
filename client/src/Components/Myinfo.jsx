import styled from "styled-components";
import "../index";
import Badge from "../Assets/Imgs/badge";
import Locate from "../Assets/Imgs/locate";
import ColorButton from "../Assets/ColorBtn";
import axios from "axios";
import { useState, useEffect } from "react";
import PostBox from "./PostBox";
import InfoEditModal from "./InfoEditModal";
import { useNavigate } from "react-router-dom";

function Myinfo() {
  const token = localStorage.getItem("accessToken");
  const memberId = localStorage.getItem("memberId");
  const [userInfo, setUserInfo] = useState({});
  const [isloading, setIsLoading] = useState(true);
  const [isModal, setIsModal] = useState(false);
  const navigate = useNavigate();

  const DeleteProfile = () => {
    axios
      .delete(`http://seb039pre029.ga:8080/v1/members/delete/${memberId}`, {
        headers: {
          Authorization: token,
        },
      })
      .then(() => {
        navigate("/");
      });
  };

  useEffect(() => {
    axios
      .get(
        `http://seb039pre029.ga:8080/v1/members/myPage/${memberId}?page=1&size=3`,
        {
          headers: {
            Authorization: token,
          },
        }
      )
      .then((res) => {
        setUserInfo(res.data);
        setIsLoading(false);
      });
  }, []);

  return (
    <>
      {isloading ? (
        ""
      ) : (
        <Wrapper>
          <UserWrapper>
            <div className="left">
              <div className="img"></div>
              <InfoWrapper>
                <div className="id">{userInfo.nickName}</div>
                <div className="reputation">
                  <Badge className="badge" />
                  {userInfo.reputation}
                </div>
                <div className="intro">{userInfo.title || "Hi"}</div>
                <div className="location">
                  <Locate className="locat" />
                  {userInfo.location || "location"}
                </div>
                <div className="summary"></div>
              </InfoWrapper>
            </div>
            <ButtonWrapper>
              <ColorButton
                mode="GREY"
                text="Edit Profile"
                onClick={() => {
                  setIsModal(true);
                }}
              />
              <ColorButton
                mode="GREY"
                text="Delete Profile"
                onClick={DeleteProfile}
              />
              <ColorButton
                mode="GREY"
                text="Logout"
                onClick={() => {
                  navigate("/logout");
                }}
              />
            </ButtonWrapper>
          </UserWrapper>
          <QuesionWrapper>
            <div className="title">
              Questions
              {userInfo.postsList.length === 0
                ? ""
                : userInfo.postsList.map((el, i) => (
                    <PostBox key={i} post={el} />
                  ))}
            </div>
            <div className="posts"></div>
          </QuesionWrapper>
          {isModal ? (
            <InfoEditModal
              userInfo={userInfo}
              setUserInfo={setUserInfo}
              setIsModal={setIsModal}
            />
          ) : null}
        </Wrapper>
      )}
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  flex-direction: column;
  padding: 30px;
  width: 100%;
  font-family: var(--sans-serif);
`;
const UserWrapper = styled.div`
  display: flex;
  justify-content: space-between;
  margin: 0 0 30px 0;
  .img {
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
    width: 200px;
    height: 200px;
    margin: 20px;
  }
  .left {
    display: flex;
  }
`;
const InfoWrapper = styled.div`
  padding: 10px;
  margin: 20px;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  font-size: var(--content-font);
  .id {
    font-size: var(--name-font);
    font-weight: 500;
  }
  .reputation {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .badge {
      fill: var(--gold);
      margin: 0 10px 0 0;
    }
  }
  .intro {
    font-style: italic;
  }
  .location {
    display: flex;
    justify-content: flex-start;
    align-items: center;
    .locat {
      fill: var(--theme-orange);
      margin: 0 10px 0 0;
    }
  }
`;
const ButtonWrapper = styled.div`
  width: 250px;
  padding: 10px;
  margin: 20px;
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
`;

const QuesionWrapper = styled.div`
  .title {
    margin: 0 0 20px 0;
    font-size: var(--ad-font);
    font-weight: bold;
  }
`;

export default Myinfo;
