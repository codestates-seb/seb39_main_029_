import styled from "styled-components";
import { IoSearchSharp } from "react-icons/io5";
import stackoverflowlogo from "../Assets/Imgs/stackoverflowlogo.png";
import { ImTrophy } from "react-icons/im";

function Nav() {
  return (
    <Container>
      <Line />
      <Navbody>
        <img src={stackoverflowlogo} className="logo" alt="react" />
        <div className="btnwrapper">
          <Search>
            <IoSearchSharp className="searchicon" />
            <SearchInput placeholder="Search.."></SearchInput>
          </Search>
        </div>
        <span className="img"></span>
        <span className="reputation">10</span>
        <span className="badge">
          <ImTrophy />
        </span>
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
  width: 100%;
  height: 47px;
  .btnwrapper {
    display: flex;
    margin-right: 20px;
  }
  .loginpbtn {
    margin-right: 10px;
  }
  .logo {
    width: 158px;
    height: 30px;
    margin-left: 56px;
  }
  .signupbtn {
    margin-left: 10px;
  }
  .img {
    width: 18px;
    height: 18px;
    border: 1px solid var(--font-color-gray);
    margin-left: 35px;
  }
  .reputation {
    font-weight: bold;
    font-size: var(--small-font);
    margin-left: 10px;
  }
  .badge {
    width: 18px;
    height: 18px;
    margin-left: 10px;
    color: gold;
  }
`;

const Search = styled.div`
  display: flex;
  align-items: center;
  width: 1100px;
  height: 33px;
  margin-left: 65px;
  border: 1px solid var(--font-color-gray);
  .searchicon {
    color: var(--font-color-gray);
    width: 23px;
    height: 23px;
    margin-left: 10px;
  }
`;

const SearchInput = styled.input`
  width: 1050px;
  height: 23px;
  border: none;
  :focus {
    outline: none;
  }
`;

export default Nav;
