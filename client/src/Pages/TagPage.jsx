import Nav from "../Components/Nav";
import Sidebar from "../Components/Sidebar";
import Tags from "../Components/Tags";
import styled from "styled-components";

function TagPage() {
  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <Tags />
      </Wrapper>
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
`;

export default TagPage;
