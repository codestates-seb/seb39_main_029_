import styled from "styled-components";
import { IoSearchSharp } from "react-icons/io5";
import Stackoverflowlogo from "../Assets/Imgs/stackoverflow.jsx";
import Badge from "../Assets/Imgs/badge";
import "../index";
import { useNavigate } from "react-router-dom";
import { useEffect } from "react";
import { useRecoilState } from "recoil";
import { UserState } from "../States/UserState.jsx";
import axios from "axios";

function Nav({ setInputText, totalPosts, setTotalPosts }) {
  const [userInfo, setUserInfo] = useRecoilState(UserState);

  useEffect(() => {
    const token = localStorage.getItem("accessToken");
    const memberId = localStorage.getItem("memberId");
    // token과 userInfo가 있을때
    if (token && userInfo.nickName !== undefined) {
      return;
    } else if (token && userInfo.nickName === undefined) {
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
        });
    }
  }, []);

  const navigate = useNavigate();
  const tohome = () => {
    navigate("/home");
  };

  const toinfo = () => {
    navigate("/info");
  };

  return (
    <Container>
      <Line />
      <Navbody>
        <Stackoverflowlogo
          className="logo"
          alt="react"
          onClick={() => tohome()}
        />
        <div className="btnwrapper">
          <Search>
            <IoSearchSharp className="searchicon" />
            <SearchInput
              placeholder="Search.."
              onChange={(e) => {
                setInputText(e.target.value);
                setTotalPosts(
                  totalPosts.filter((el) => {
                    return el.subject
                      .toLowerCase()
                      .includes(e.target.value.toLocaleLowerCase());
                  })
                );
              }}
            ></SearchInput>
          </Search>
        </div>
        <div className="profile">
          <span className="img" onClick={() => toinfo()}></span>
          <span className="reputation">{userInfo.reputation}</span>
          <Badge className="badge" />
        </div>
      </Navbody>
    </Container>
  );
}

const Container = styled.div`
  width: 100%;
  height: 50px;
  background-color: #f8f9f9;
  box-shadow: 0 1px 2px lightgray;
  font-family: var(--sans-serif);
`;
const Line = styled.div`
  width: 100%;
  height: 3px;
  background-color: #f48225;
`;
const Navbody = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100;
  height: 47px;
  .logo {
    margin: 0 30px 0 0;
  }
  .profile {
    display: flex;
    justify-content: center;
    align-items: center;
    margin: 0 0 0 30px;
  }
  .img {
    width: 30px;
    height: 30px;
    margin: 0 10px 0 0;
    border: 1px solid hsl(210, 8%, 85%);
    border-radius: 5px;
  }
  .reputation {
    font-weight: bold;
    font-size: var(--normal-font);
  }
  .badge {
    fill: var(--gold);
    margin: 0 30px 0 20px;
  }
`;

const Search = styled.div`
  display: flex;
  align-items: center;
  width: 800px;
  height: 33px;
  border: 1px solid hsl(210, 8%, 85%);
  border-radius: 5px;
  .searchicon {
    color: var(--font-color-gray);
    width: 23px;
    height: 23px;
    margin: 0 10px 0 10px;
  }
`;

const SearchInput = styled.input`
  width: 1050px;
  height: 23px;
  border: none;
  background-color: #f8f9f9;
  :focus {
    outline: none;
  }
`;

export default Nav;
