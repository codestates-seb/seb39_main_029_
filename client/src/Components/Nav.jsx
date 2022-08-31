import styled from "styled-components";
import { IoSearchSharp } from "react-icons/io5";
import Stackoverflowlogo from "../Assets/Imgs/stackoverflow.jsx";
import Badge from "../Assets/Imgs/badge";
import "../index";
import { useNavigate } from "react-router-dom";

function Nav() {
  const navigate = useNavigate();

  const tohome = () => {
    navigate("/home");
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
            <SearchInput placeholder="Search.."></SearchInput>
          </Search>
        </div>
        <div className="profile">
          <span className="img"></span>
          <span className="reputation">10</span>
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
  justify-content: space-between;
  width: 100;
  height: 47px;
  .logo {
    margin: 0 0 0 30px;
  }
  .btnwrapper {
  }
  .profile {
    display: flex;
    justify-content: center;
    align-items: center;
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
