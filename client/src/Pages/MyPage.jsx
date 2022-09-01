import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import Myinfo from "../Components/Myinfo";
import styled from "styled-components";

function MyPage() {
  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <Myinfo />
      </Wrapper>
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  height: 100vmax;
`;

export default MyPage;
