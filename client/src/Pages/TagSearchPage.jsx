import TagSearch from "../Components/TagSearch";
import Sidebar from "../Components/Sidebar";
import Nav from "../Components/Nav";
import styled from "styled-components";

function TagSearchPage() {
  return (
    <>
      <Nav />
      <Wrapper>
        <Sidebar />
        <TagSearch />
      </Wrapper>
    </>
  );
}

const Wrapper = styled.div`
  display: flex;
  justify-content: center;
`;

export default TagSearchPage;
