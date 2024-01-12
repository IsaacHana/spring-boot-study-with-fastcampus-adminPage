interface SelectBoxProps {
  options: {
    value: string;
    name: string;
  }[];
  defaultValue: string;
  onChangePageSize: (value: number) => void;
}

const SelectBox = (props: SelectBoxProps) => {
  const handleCurrentPage = (e: React.ChangeEvent<HTMLSelectElement>) => {
    props.onChangePageSize(parseInt(e.target.value));
  };
  return (
    <select
      onChange={handleCurrentPage}
      className="bg-gray-50 border border-gray-300 text-gray-900 text-sm rounded-lg focus:ring-blue-500 focus:border-blue-500 block w-20 p-2.5 dark:bg-gray-700 dark:border-gray-600 dark:placeholder-gray-400 dark:text-white dark:focus:ring-blue-500 dark:focus:border-blue-500"
    >
      {props.options.map((option) => (
        <option
          value={option.value}
          defaultValue={props.defaultValue === option.value ? "selected" : ""}
        >
          {option.name}
        </option>
      ))}
    </select>
  );
};

export default SelectBox;
